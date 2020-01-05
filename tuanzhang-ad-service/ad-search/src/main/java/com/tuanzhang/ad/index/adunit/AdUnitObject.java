package com.tuanzhang.ad.index.adunit;

import com.tuanzhang.ad.index.adPlan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {

    private Long unitId;

    private Integer unitStatus;

    private Integer posoyionType;

    private Long planId;

    private AdPlanObject adPlanObject;
}
