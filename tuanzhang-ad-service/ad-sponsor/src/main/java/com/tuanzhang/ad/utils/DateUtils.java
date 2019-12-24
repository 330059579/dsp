package com.tuanzhang.ad.utils;

import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.ast.Or;

import java.text.ParseException;
import java.util.Date;

public class DateUtils {

    private static  String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
    };

    public static Date parseDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }

        try {
            Date date1 = org.apache.commons.lang.time.DateUtils.parseDate(date, parsePatterns);
            return date1;
        } catch (ParseException e) {
            return null;
        }
    }
}
