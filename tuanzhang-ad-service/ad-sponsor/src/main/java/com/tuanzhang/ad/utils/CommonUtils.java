package com.tuanzhang.ad.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class CommonUtils {
    public static String md5(String str) {
        return DigestUtils.md5Hex(str).toUpperCase();
    }
}


