package com.tuanzhang.ad.vo;

import org.apache.commons.lang.StringUtils;

public class AdUnitRequest {

    private Long planId;

    private String unitName;

    private Integer positionType;

    private Integer budget;

    public Boolean checkParams(){
        return planId != null && StringUtils.isNotBlank(unitName) && null != positionType && null != budget;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }
}
