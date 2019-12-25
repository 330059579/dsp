package com.tuanzhang.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordRequest {

    private List<UnitkeyWord> unitkeyWords;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class UnitkeyWord{
        private Long unitId;
        private String keyWorld;
    }
}
