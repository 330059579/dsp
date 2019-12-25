package com.tuanzhang.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitRequest {

    private List<CreativeUnitItem> list;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreativeUnitItem{
        private Long creativeId;

        private Long unitId;
    }
}
