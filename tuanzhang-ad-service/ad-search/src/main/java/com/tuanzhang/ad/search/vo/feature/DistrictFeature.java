package com.tuanzhang.ad.search.vo.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DistrictFeature {

    private List<ProvinceAndCity> districts;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ProvinceAndCity{
        private String province;

        private String city;
    }
}
