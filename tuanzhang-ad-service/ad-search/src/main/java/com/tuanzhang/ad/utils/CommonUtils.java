package com.tuanzhang.ad.utils;

import org.apache.commons.lang.time.DateUtils;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class CommonUtils {


    public static <K, V> V getorCreate(K key, Map<K, V> map, Supplier<V> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }

    public static String stringConcat(String... args){
        StringBuilder result
                 = new StringBuilder();
        for (String arg: args) {
            result.append(arg).append("_");
        }

        result.deleteCharAt(result.length() -1);
        return result.toString();
    }

    public static Date parseDate(String dateString){
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            return DateUtils.addHours(dateFormat.parse(dateString), -8);
        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
