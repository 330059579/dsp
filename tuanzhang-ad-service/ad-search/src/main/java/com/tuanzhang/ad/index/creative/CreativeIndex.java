package com.tuanzhang.ad.index.creative;

import com.tuanzhang.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class CreativeIndex implements IndexAware<Long, CreativeObject> {

    private static Map<Long, CreativeObject>  creativeMap;

    static {
        creativeMap = new ConcurrentHashMap<>();
    }


    public List<CreativeObject> fetch(Collection<Long> adIds) {
        if (CollectionUtils.isEmpty(adIds)) {
           return Collections.emptyList();
        }

        List<CreativeObject> result = new ArrayList<>();

        adIds.forEach(u ->{
            CreativeObject object = get(u);
            if (null == object) {
                return;
            }
            result.add(object);
        });

        return result;
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
