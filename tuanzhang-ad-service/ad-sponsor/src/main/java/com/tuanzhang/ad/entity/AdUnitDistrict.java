package com.tuanzhang.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ad_unit_district")
@Entity
public class AdUnitDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    @Basic
    @Column(name = "province", nullable = false)
    private String provice;

    @Basic
    @Column(name = "city", nullable = false)
    private String city;

    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createDate;


    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateDate;

    public AdUnitDistrict(Long unitId, String provice, String city) {
        this.unitId = unitId;
        this.provice = provice;
        this.city = city;
        this.createDate = new Date();
        this.updateDate = new Date();
    }


}
