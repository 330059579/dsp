package com.tuanzhang.ad.vo;


import com.fasterxml.jackson.databind.util.BeanUtil;
import com.tuanzhang.ad.entity.AdCreative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitCreativeRequest {

    private String name;

    private  Integer type;

    private Integer materialType;

    private Integer height;

    private Integer width;

    private Integer size;

    private Integer duration;

    private Integer auditStatus;

    private Long userId;

    private String url;

    public AdCreative convertCreative(){
        AdCreative creative = new AdCreative();
        creative.setName(name);
        creative.setType(type);
        creative.setMaterialType(materialType);
        creative.setHeight(height);
        creative.setWidth(width);
        creative.setSize(size);
        creative.setDuration(duration);
        creative.setAuditStatus(auditStatus);
        creative.setUserId(userId);
        creative.setUrl(url);
        creative.setCreateTime(new Date());
        creative.setUpdateTime(creative.getCreateTime());
        return creative;
    }

}
