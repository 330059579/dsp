package com.tuanzhang.ad.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ad_creative_unit")
public class AdCreativeUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "creative_id")
    private Long creativeId;


    @Basic
    @Column(name = "unit_id")
    private Long unitId;

    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public AdCreativeUnit(Long creativeId, Long unitId) {
        this.creativeId = creativeId;
        this.unitId = unitId;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
