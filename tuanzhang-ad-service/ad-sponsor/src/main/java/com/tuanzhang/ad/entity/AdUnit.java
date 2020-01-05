package com.tuanzhang.ad.entity;

import com.tuanzhang.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ad_unit")
@Entity
public class AdUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "plan_id", nullable = false)
    private Long planId;

    @Basic
    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @Basic
    @Column(name = "unit_status", nullable = false)
    private Integer unitStatus;

    @Basic
    @Column(name = "position_type", nullable = false)
    //广告位类型，开屏，贴片（电影开始），中贴，暂停贴 。。。。。
    private Integer positionType;

    @Basic
    @Column(name = "budget", nullable = false)
    private Integer budget;

    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createDate;


    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateDate;

    public AdUnit(Long planId, String unitName, Integer positionType,
                  Integer budget) {
        this.planId = planId;
        this.unitName = unitName;
        this.unitStatus = CommonStatus.VALID.getStatus();
        this.positionType = positionType;
        this.budget = budget;
        this.createDate = new Date();
        this.updateDate = new Date();
    }
}
