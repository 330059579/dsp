package com.tuanzhang.ad.index.creative;

import com.tuanzhang.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class CreativeIndex implements IndexAware<Long, CreativeObject> {

    private static Map<Long, CreativeObject>  creativeMap;

    static {
        creativeMap = new ConcurrentHashMap<>();
    }


    @Override
    public CreativeObject get(Long key) {
        return creativeMap.get(key);
    }

    @Override
    public void add(Long key, CreativeObject value) {
        creativeMap.put(key, value);
    }

    @Override
    public void update(Long key, CreativeObject value) {
        CreativeObject creativeObject = creativeMap.get(key);
        if (null == creativeObject) {
            creativeMap.put(key, value);
            return;
        }
        creativeObject.update(value);
    }

    @Override
    public void delete(Long key, CreativeObject value) {
        creativeMap.remove(key);
    }
}
