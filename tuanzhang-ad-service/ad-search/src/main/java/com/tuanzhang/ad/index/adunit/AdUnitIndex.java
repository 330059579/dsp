package com.tuanzhang.ad.index.adunit;

import com.tuanzhang.ad.index.IndexAware;
import com.tuanzhang.ad.index.adPlan.AdPlanObject;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AdUnitIndex implements IndexAware<Long, AdUnitObject> {

    private static Map<Long, AdUnitObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<Long, AdUnitObject>();
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
