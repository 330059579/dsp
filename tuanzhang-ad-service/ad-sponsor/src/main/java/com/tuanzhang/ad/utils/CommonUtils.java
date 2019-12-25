package com.tuanzhang.ad.utils;

import com.tuanzhang.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

public class CommonUtils {

    public static String[] parsePatterns = {
          "yyyy-MM-dd","yyyy/MM/dd","yyyy.MM.dd"
    };
    public static String md5(String str) {
        return DigestUtils.md5Hex(str).toUpperCase();
    }
}


