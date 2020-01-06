package com.tuanzhang.ad.index.keyword;

import com.tuanzhang.ad.index.IndexAware;
import com.tuanzhang.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
public class UnitKeyWord implements IndexAware<String, Set<Long>> {

    private static Map<String, Set<Long>> keywordUnitMap;
    private static Map<Long, Set<String>> uniyKeywordMap;

    static {
        keywordUnitMap = new ConcurrentHashMap<>();
        uniyKeywordMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        if (StringUtils.isEmpty(key)) {
            return Collections.emptySet();
        }

        Set<Long> result = keywordUnitMap.get(key);
        if (result == null) {
            return Collections.emptySet();
        }

        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {
        Set<Long> unitIdSet = CommonUtils.getorCreate(key, keywordUnitMap, ConcurrentSkipListSet::new);
        unitIdSet.addAll(value);

        for (Long unitId : value) {
            Set<String> keywordSet = CommonUtils.getorCreate(unitId, uniyKeywordMap, ConcurrentSkipListSet::new);
            keywordSet.add(key);
        }
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("不更新");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        Set<Long> unitIds = CommonUtils.getorCreate(key, keywordUnitMap, ConcurrentSkipListSet::new);
        unitIds.removeAll(value);
        for(Long unitId : value) {
            Set<String> keywordSet = CommonUtils.getorCreate(unitId, uniyKeywordMap, ConcurrentSkipListSet::new);
            keywordSet.remove(key);
        }
    }


    public boolean match(Long unitId, List<String> keywords){
        if (uniyKeywordMap.containsKey(unitId)
             && CollectionUtils.isNotEmpty(uniyKeywordMap.get(unitId))){
            Set<String> unitKeywords = uniyKeywordMap.get(unitId);
            //判断a是否是b的子集合，即b完全包含a
            return CollectionUtils.isSubCollection(keywords, unitKeywords);
        }

        return false;
    }
}
