package com.tuanzhang.ad.index.district;

import com.tuanzhang.ad.index.IndexAware;
import com.tuanzhang.ad.search.vo.feature.DistrictFeature;
import com.tuanzhang.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.set.CompositeSet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UnitDistrictIndex  implements IndexAware<String, Set<Long>> {

    private static Map<String, Set<Long>> districtUnitMap;
    private static Map<Long, Set<String>> unitDistrictMap;

    static {
        districtUnitMap  = new ConcurrentHashMap<>();
        unitDistrictMap = new ConcurrentHashMap<>();
    }
    public boolean match(Long adUnitId, List<DistrictFeature.ProvinceAndCity> districtList){
        if (unitDistrictMap.containsKey(adUnitId) && CollectionUtils.isNotEmpty(unitDistrictMap.get(adUnitId))) {
            Set<String> unitDistrict = unitDistrictMap.get(adUnitId);
            List<String> collect = districtList.stream()
                    .map(d -> CommonUtils.stringConcat(d.getProvince(), d.getCity())).collect(Collectors.toList());
            return CollectionUtils.isSubCollection(collect, unitDistrict);
        }

        return false;
    }

    @Override
    public Set<Long> get(String key) {
        return districtUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        Set<Long> unitIds = CommonUtils.getorCreate(key, districtUnitMap, ConcurrentSkipListSet::new);
        unitIds.addAll(value);
        for(Long unitId:value) {
            Set<String> strings = CommonUtils.getorCreate(unitId, unitDistrictMap, ConcurrentSkipListSet::new);
            strings.add(key);
        }
    }

    @Override
    public void update(String key, Set<Long> value) {

    }

    @Override
    public void delete(String key, Set<Long> value) {
        Set<Long> longs = CommonUtils.getorCreate(key, districtUnitMap, ConcurrentSkipListSet::new);
        longs.removeAll(value);
        for(Long unitid:value) {
            Set<String> strings = CommonUtils.getorCreate(unitid, unitDistrictMap, ConcurrentSkipListSet::new);
            strings.remove(key);
        }
    }
}
