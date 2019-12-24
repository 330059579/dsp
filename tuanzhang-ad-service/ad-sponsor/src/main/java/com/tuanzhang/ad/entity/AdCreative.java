package com.tuanzhang.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ad_creative")
public class AdCreative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "type", nullable = false)
    private  Integer type;

    @Basic
    @Column(name = "material_type", nullable = false)
    private Integer materialType;

    @Basic
    @Column(name = "height", nullable = false)
    private Integer height;

    @Basic
    @Column(name = "width", nullable = false)
    private Integer width;

    @Basic
    @Column(name = "size", nullable = false)
    private Integer size;

    @Basic
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Basic
    @Column(name = "audit_status", nullable = false)
    private Integer auditStatus;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "url", nullable = false)
    private String url;

    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;



}
