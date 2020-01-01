package com.tuanzhang.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients //使用feign访问其他微服务
@EnableEurekaClient //作为一个EurekaClient 存在
@EnableHystrix //使用断路器
@EnableCircuitBreaker //断路器
@EnableDiscoveryClient //微服务的发现
@EnableHystrixDashboard //微服务监控
@SpringBootApplication //启动
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    @Bean
    @LoadBalanced //如果系统有多个实例，该注解可以实现负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
