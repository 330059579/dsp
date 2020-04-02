package com.tuanzhang.ad.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;

@Component
@ConfigurationProperties(prefix = "adconf.mysql")  //实现配置文件到实体类的转换
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinLogConfig {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String binlogName;

    private Long position;


}
