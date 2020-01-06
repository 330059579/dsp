package com.tuanzhang.ad.index.adPlan;

import com.tuanzhang.ad.index.IndexAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AdPlanIndex implements IndexAware<Long, AdPlanObject> {

    private static Map<Long, AdPlanObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<Long, AdPlanObject>();
    }
    @Override
    public AdPlanObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdPlanObject value) {
        objectMap.put(key, value);
    }

    @Override
    public void update(Long key, AdPlanObject value) {
        AdPlanObject oldObject = objectMap.get(key);
        if (null == oldObject) {
            objectMap.put(key, value);
        }else {
            oldObject.update(value);
        }
    }

    @Override
    public void delete(Long key, AdPlanObject value) {
        objectMap.remove(key);
    }
}
