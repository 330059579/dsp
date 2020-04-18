package com.tuanzhang.ad.search.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tuanzhang.ad.constant.CommonStatus;
import com.tuanzhang.ad.index.DataTable;
import com.tuanzhang.ad.index.adunit.AdUnitIndex;
import com.tuanzhang.ad.index.adunit.AdUnitObject;
import com.tuanzhang.ad.index.creative.CreativeIndex;
import com.tuanzhang.ad.index.creative.CreativeObject;
import com.tuanzhang.ad.index.creativeunit.CreativeUnitIndex;
import com.tuanzhang.ad.index.district.UnitDistrictIndex;
import com.tuanzhang.ad.index.interest.UnitItIndex;
import com.tuanzhang.ad.index.keyword.UnitKeyWord;
import com.tuanzhang.ad.search.ISearch;
import com.tuanzhang.ad.search.vo.SearchRequest;
import com.tuanzhang.ad.search.vo.SearchResponse;
import com.tuanzhang.ad.search.vo.feature.DistrictFeature;
import com.tuanzhang.ad.search.vo.feature.FeatureRelation;
import com.tuanzhang.ad.search.vo.feature.ItFeature;
import com.tuanzhang.ad.search.vo.feature.KeywordFeature;
import com.tuanzhang.ad.search.vo.media.AdSlot;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class SearchImpl implements ISearch {

    public SearchResponse fallback(SearchRequest request, Throwable e) {
        return null;
    }

    @Override
    //定义在方法上面，如果该方法超时，或失败，则调用指定的消费降级方法fallback, fallback必须和fetch在同一类中
    //使用HystrixCommand注解时，启动类上必须有EnableCircuitBreaker注解，原理是定义了这个注解后，或通过aop拦截所有加了HystrixCommand的方法，并将方法扔到
    //Hystrix线程池中，发生异常后，通过反射调用同类中的fallback
    //注意 HystrixCommand 效率很低，一般只和Fein结合使用
    @HystrixCommand(fallbackMethod = "fallback")
    public SearchResponse fetch(SearchRequest request) {
        List<AdSlot> adSlots = request.getRequestInfo().getAdSlots();
        KeywordFeature keywordFeature = request.getFeatureInfo().getKeywordFeature();
        DistrictFeature districtFeature = request.getFeatureInfo().getDistrictFeature();
        ItFeature itFeature = request.getFeatureInfo().getItFeature();
        FeatureRelation relation = request.getFeatureInfo().getRelation();

        //构造响应对象
        SearchResponse response = new SearchResponse();
        Map<String, List<SearchResponse.Creative>> adSlot2Ads = response.getAdSlot2Ads();
        adSlots.forEach(u -> {
            Set<Long> targetUnitIdSet;
            Set<Long> adUnitIdSet = DataTable.of(AdUnitIndex.class).match(u.getPositionType());
            if (relation == FeatureRelation.AND) {
                filterKeywordFeature(adUnitIdSet, keywordFeature);
                filterDisttictFeature(adUnitIdSet, districtFeature);
                filterItFeature(adUnitIdSet, itFeature);
                targetUnitIdSet = adUnitIdSet;
            }else {
               targetUnitIdSet =  getORRelationUnitIds(adUnitIdSet, keywordFeature, districtFeature, itFeature);
            }

            List<AdUnitObject> unitObjects = DataTable.of(AdUnitIndex.class).fetch(targetUnitIdSet);
            filterAdUnitAndPlanStatus(unitObjects, CommonStatus.VALID);
            List<Long> adIds = DataTable.of(CreativeUnitIndex.class).selectAds(unitObjects);
            List<CreativeObject> creativeObjects = DataTable.of(CreativeIndex.class).fetch(adIds);

            //通过adSlot进行过滤
            filterCreativeByAdSlot(creativeObjects, u.getWidth(), u.getHeight(), u.getType());
            adSlot2Ads.put(u.getAdSlotCode(), buildCreative(creativeObjects));
        });
        return response;
    }

    private Set<Long> getORRelationUnitIds(Set<Long> adUnitIdSet, KeywordFeature keywordFeature, DistrictFeature districtFeature, ItFeature itFeature){
        if (CollectionUtils.isEmpty(adUnitIdSet)) {
            return Collections.emptySet();
        }

        Set<Long> keywordUnitIdSet = new HashSet<>(adUnitIdSet);
        Set<Long> districtUnitIdSet = new HashSet<>(adUnitIdSet);
        Set<Long> itUnitIdSet = new HashSet<>(adUnitIdSet);

        filterKeywordFeature(keywordUnitIdSet, keywordFeature);
        filterDisttictFeature(districtUnitIdSet, districtFeature);
        filterItFeature(itUnitIdSet, itFeature);

        return new HashSet<>(CollectionUtils.union(CollectionUtils.union(keywordUnitIdSet, districtUnitIdSet),itUnitIdSet));
    }

    private void filterKeywordFeature(Collection<Long> adUnitIds, KeywordFeature keywordFeature) {
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(keywordFeature.getKeywords())) {
           /* CollectionUtils.filter(adUnitIds, adUnitIds -> DataTable.of(UnitKeyw.c))*/
        }
    }

    private void filterDisttictFeature(Collection<Long> adUnitIds, DistrictFeature districtFeature){
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(districtFeature.getDistricts())) {
             CollectionUtils.filter(adUnitIds, adUnitId -> DataTable.of(UnitDistrictIndex.class).match(adUnitId, districtFeature.getDistricts()));
        }
    }


    private void filterItFeature(Collection<Long> adUnitIds, ItFeature itFeature){
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(itFeature.getIts())) {
            CollectionUtils.filter(adUnitIds, adUnitId -> DataTable.of(UnitItIndex.class).match(adUnitId, itFeature.getIts()));
        }
    }

    private void filterAdUnitAndPlanStatus(List<AdUnitObject> unitObjects, CommonStatus status){
        if (CollectionUtils.isEmpty(unitObjects)) {
            return;
        }

        CollectionUtils.filter(unitObjects, adUnitObject -> adUnitObject.getUnitStatus().equals(status.getStatus())
                && adUnitObject.getAdPlanObject().getPlanStatus().equals(status.getStatus()));
    }

    private void filterCreativeByAdSlot(List<CreativeObject> creativeObjects, Integer width, Integer height, List<Integer> type){
        if (CollectionUtils.isEmpty(creativeObjects)) {
            return;
        }

        CollectionUtils.filter(creativeObjects, creative -> creative.getAuidStatus().equals(CommonStatus.VALID.getStatus())
                  && creative.getWidth().equals(width)
                  && creative.getHeight().equals(height)
                  && type.contains(creative.getType()));
    }

    private List<SearchResponse.Creative> buildCreative(List<CreativeObject> creativeObjects) {
        if (CollectionUtils.isEmpty(creativeObjects)) {
            return Collections.emptyList();
        }

        CreativeObject randomObject = creativeObjects.get(Math.abs(new Random().nextInt() % creativeObjects.size()));

        return Collections.singletonList(SearchResponse.convert(randomObject));
    }
}
