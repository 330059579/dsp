package com.tuanzhang.ad.index.creative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeObject {

    private Long adId;

    private String name;

    private Integer type;

    private Integer materialType;

    private Integer heigit;

    private Integer width;

    private Integer aduiotStatus;

    private String adUrl;

    public void update(CreativeObject object) {
        if (null != object.getAdId()) {
            this.adId = object.getAdId();
        }

        if (null != object.getName()) {
            this.name = object.getName();
        }

        if (null != object.getType()) {
            this.type = object.getType();
        }

        if (null != object.getMaterialType()) {
            this.materialType = object.getMaterialType();
        }

        if (null != object.getHeigit()) {
            this.heigit = object.getHeigit();
        }

        if (null != object.getWidth()) {
            this.width = object.getWidth();
        }

        if (null != object.getAduiotStatus()){
            this.aduiotStatus = object.getAduiotStatus();
        }

        if (null != object.getAdUrl()) {
            this.adUrl = object.getAdUrl();
        }
    }
}
