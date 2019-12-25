package com.tuanzhang.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.tuanzhang.ad.service")
@EnableJpaRepositories(basePackages = "com.tuanzhang.ad.dao")
public class SponsortApplication {

    public static void main(String[] args) {
        SpringApplication.run(SponsortApplication.class, args);
    }
}
