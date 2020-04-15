package com.tuanzhang.ad.index;

import lombok.Getter;

@Getter
public enum  DataLevel {

    LEVEL2(2, "level 2"),
    LEVEL3(3, "level 3"),
    LEVEL4(4, "level 4");

    private Integer level;

    private String desc;

    DataLevel(Integer level, String desc) {
        this.level = level;
        this.desc = desc;
    }
}
