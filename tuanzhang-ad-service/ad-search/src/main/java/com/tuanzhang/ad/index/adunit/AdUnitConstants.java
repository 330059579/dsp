package com.tuanzhang.ad.index.adunit;

public class AdUnitConstants {

    public static class POSITIONTYPE {
        //1,2,4,8,16 用的二进制大小，为了使用位或位与运算加快检索速度
        //开屏广告
        public static  final int KAIPING = 1;
        //贴片 电影开头
        public static final  int TIEPIAN = 2;
        //中贴  电影中间
        public static final  int TIEPIAN_MIDDLE =4;
        //暂停贴  电影暂停
        public static final  int TIEPIAN_PAUSE  = 8;
        //后贴
        public static final  int TIEPIAN_POST  = 16;
    }
}
