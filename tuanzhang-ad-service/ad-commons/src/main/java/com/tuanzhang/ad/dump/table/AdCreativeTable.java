package com.tuanzhang.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCreativeTable {

    private Long adId;

    private String name;

    private Integer type;

    private Integer materialType;

    private Integer heigit;

    private Integer width;

    private Integer aduiotStatus;

    private String adUrl;
}
