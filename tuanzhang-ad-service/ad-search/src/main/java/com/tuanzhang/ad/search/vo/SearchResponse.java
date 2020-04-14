package com.tuanzhang.ad.search.vo;

import com.tuanzhang.ad.index.creative.CreativeObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

    public Map<String, List<Creative>> adSlot2Ads = new HashMap<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Creative{
        private Long adId;
        private String adUrl;
        private Integer width;
        private Integer height;
        private Integer type;
        private Integer matialType;

        //展示检测url
        private List<String> showMonitorUrl = Arrays.asList("www.tuanzhang.com","www.tuanzhang.com");
        //点击检测url
        private List<String> clickMonitorUrl = Arrays.asList("www.tuanzhang.com","www.tuanzhang.com");

    }

    public static Creative convert(CreativeObject object) {
        Creative creative = new Creative();
        BeanUtils.copyProperties(object, creative);
        return creative;
    }
}
