package com.tuanzhang.ad.client.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanGetRquest {

    private Long userId;

    private List<Long> ids;
}
