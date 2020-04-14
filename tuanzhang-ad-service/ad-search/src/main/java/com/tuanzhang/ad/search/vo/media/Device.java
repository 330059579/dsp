package com.tuanzhang.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    //设备id
    private String deviceCode;

    private String mac;

    private String ip;

    //机器型号
    private String model;

    //分辨率
    private String dispalySize;

    //屏幕尺寸
    private String screenSize;

    //设备序列号
    private String serialName;
}
