package com.tuanzhang.ad.index.adunit;

import com.tuanzhang.ad.index.IndexAware;
import com.tuanzhang.ad.index.adPlan.AdPlanObject;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AdUnitIndex implements IndexAware<Long, AdUnitObject> {

    private static Map<Long, AdUnitObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<Long, AdUnitObject>();
    }

    public Set<Long> match(Integer positionType) {
        Set<Long> adUnitIds = new HashSet<>();
        objectMap.forEach((k,v) -> {
            if (AdUnitObject.isAdSlotTypeOK(positionType, v.getPosoyionType())) {
                adUnitIds.add(k);
            }
        });
        return adUnitIds;
    }

    public List<AdUnitObject> fetch(Collection<Long> adUnitIds) {
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return null;
        }

        List<AdUnitObject> resultList = new ArrayList<>();
        adUnitIds.forEach( u -> {
            AdUnitObject adUnitObject = get(u);
            if (adUnitObject != null) {
                resultList.add(adUnitObject);
            }
        });

        return resultList;
    }
    @Override
    public AdUnitObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {
        objectMap.put(key, value);
    }

    @Override
    public void update(Long key, AdUnitObject value) {
        System.out.println("暂不更新");
    }

    @Override
    public void delete(Long key, AdUnitObject value) {
        objectMap.remove(key, value);
    }
}
