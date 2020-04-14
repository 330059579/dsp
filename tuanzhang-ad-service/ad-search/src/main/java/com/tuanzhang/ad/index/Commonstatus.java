package com.tuanzhang.ad.index;

import com.tuanzhang.ad.constant.CommonStatus;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
public enum  Commonstatus {

    VALID(1, "有效状态"),
    INVALID(2, "无效状态");

    private Integer status;

    private String desc;

    Commonstatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
