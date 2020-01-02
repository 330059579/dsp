package com.tuanzhang.ad.index;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.applet.AppletContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataTable implements ApplicationContextAware, PriorityOrdered {

    private static ApplicationContext applicationContext;

    public static final Map<Class, Object> dataAbleMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DataTable.applicationContext = applicationContext;
    }

    //定义bean的初始化顺序，先初始化有顺序的，再初始化没有设置排序的
    @Override
    public int getOrder() {
        //int类型的最小值，保证最先初始化
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }


    @SuppressWarnings("all")
    public static <T> T of(Class<T> clazz) {
        T instance = (T) dataAbleMap.get(clazz);
        if (null != instance) {
            return instance;
        }

        dataAbleMap.put(clazz, bean(clazz));
        return (T) dataAbleMap.get(clazz);
    }

    @SuppressWarnings("all")
    private static <T> T bean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    @SuppressWarnings("all")
    private static <T> T bean(Class clazz) {
        return (T) applicationContext.getBean(clazz);
    }
}
