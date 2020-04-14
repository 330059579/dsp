package com.tuanzhang.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdSlot {

    //广告位编码
    private String adSlotCode;

    //流量类型
    private Integer positionType;

    //宽高
    private Integer width;

    private Integer height;

    //物料类型 视频 图片
    private List<Integer> type;

    //最低出价
    private Integer minCpm;
}
