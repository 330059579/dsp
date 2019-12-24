package com.tuanzhang.ad.entity;

import com.tuanzhang.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ad_plan")
public class AdPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Basic
    @Column(name = "plan_status", nullable = false)
    private Integer planStatus;

    @Basic
    @Column(name = "start_date", nullable = false)
    private Date startDate;


    @Basic
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Basic
    @Column(name = "create_date", nullable = false)
    private Date createDate;


    @Basic
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    public AdPlan(Long userId, String planName, Date startDate, Date endDate) {
        this.userId = userId;
        this.planName = planName;
        this.planStatus = CommonStatus.VALID.getStatus();
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = new Date();
        this.updateDate = new Date();
    }




}
