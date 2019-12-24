package com.tuanzhang.ad.constant;

public enum MaterialType {

    JPG(1, "jpg"),
    GIF(2, "gif"),
    BMP(3, "bmp"),

    MP4(4, "mp4"),
    AVI(5, "avi"),

    TXT(6, "txt");

    private int type;
    private String desc;

    MaterialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
