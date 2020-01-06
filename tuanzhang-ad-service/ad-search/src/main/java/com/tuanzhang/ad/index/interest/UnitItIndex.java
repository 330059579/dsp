package com.tuanzhang.ad.index.interest;

import com.tuanzhang.ad.index.IndexAware;
import com.tuanzhang.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.set.CompositeSet;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class UnitItIndex implements IndexAware<String, Set<Long>> {

    private static Map<String,Set<Long>> itUnitMap;

    private static Map<Long, Set<String>> unitItMap;

    static {
        itUnitMap = new ConcurrentHashMap<>();
        unitItMap = new ConcurrentHashMap<>();
    }
    @Override
    public Set<Long> get(String key) {
        return itUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        Set<Long> unitIds = CommonUtils.getorCreate(key, itUnitMap, CompositeSet::new);
        unitIds.addAll(value);

        for (Long unitId: value) {
            Set<String> strings = CommonUtils.getorCreate(unitId, unitItMap, CompositeSet::new);
            strings.add(key);
        }
    }

    @Override
    public void update(String key, Set<Long> value) {
        System.out.println("不支持更新");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        Set<Long> unitIds = CommonUtils.getorCreate(key, itUnitMap, CompositeSet::new);
        unitIds.removeAll(value);
        for (Long unitId: value) {
            Set<String> strings = CommonUtils.getorCreate(unitId, unitItMap, CompositeSet::new);
            strings.remove(key);
        }
    }

    public boolean match(Long unitId, List<String> itTags){
        if (unitItMap.containsKey(unitId) && CollectionUtils.isNotEmpty(unitItMap.get(unitId))) {
            Set<String> strings = unitItMap.get(unitId);
            return CollectionUtils.isSubCollection(itTags, strings);
        }

        return false;
    }
}
