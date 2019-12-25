package com.tuanzhang.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ad_unit_keyword")
@Entity
public class AdUnitKeyWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    @Basic
    @Column(name = "keyword", nullable = false)
    private String keyWord;

    @Basic
    @Column(name = "create_date", nullable = false)
    private Date createDate;


    @Basic
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    public AdUnitKeyWord(Long unitId, String keyWord) {
        this.unitId = unitId;
        this.keyWord = keyWord;
        this.createDate = new Date();
        this.updateDate = new Date();
    }


}
