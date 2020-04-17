package com.tuanzhang.ad.controller;

import com.tuanzhang.ad.annotation.IgnoreResponseAdvice;
import com.tuanzhang.ad.client.SponsorClient;
import com.tuanzhang.ad.client.vo.AdPlan;
import com.tuanzhang.ad.client.vo.AdPlanGetRquest;
import com.tuanzhang.ad.search.ISearch;
import com.tuanzhang.ad.search.vo.SearchRequest;
import com.tuanzhang.ad.search.vo.SearchResponse;
import com.tuanzhang.ad.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

//Ribbon 用来实现负载均衡和远程调用
/*
   启动类注册RestTemplate
   @Bean
    @LoadBalanced //如果系统有多个实例，该注解可以实现负载均衡,如果投放系统有多个，可以轮询调用
    RestTemplate restTemplate(){
        return new RestTemplate();}
       -------------------------------------------
    Controller中注入   private final RestTemplate restTemplate;
    --------------------------------------------------------------
    调用服务
    restTemplate.postForEntity(
                "http://eureka-client-ad-sponsor/ad-sponsor/get/plan",
                request,
                BaseResult.class
        ).getBody();

*/

//feign 调用  ，现实中一般用feign
/*

启动类添加注解
@EnableFeignClients //使用feign访问其他微服务
@EnableEurekaClient //作为一个EurekaClient 存在
@EnableHystrix //使用断路器
@EnableCircuitBreaker //断路器
@EnableDiscoveryClient //微服务的发现
@EnableHystrixDashboard //微服务监控

---------------------------------------------------------
  feign:
  hystrix:
    enabled: true

management:
  endpoints:
    web:
      exposure:
         include: "*" #暴露全部监控信息

 -------------------------------------------------------------

 创建一个查询接口
 eureka-client-ad-sponsor是要调用服务的名称
@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClientHystrix.class) //服务降级，指定断路器
public interface SponsorClient {

    //定义一个接口就可以使用feign调用
    @RequestMapping(value = "/ad-sponsor/get/plan", method = RequestMethod.POST)
    BaseResult<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRquest request);
}

-----------------------------------------------------------
Controller中调用查询接口

   注入接口
   @Resource
    private SponsorClient sponsorClient;

@IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    public BaseResult<List<AdPlan>> getAdPlanByFeign(@RequestBody AdPlanGetRquest request){
        return sponsorClient.getAdPlans(request);
    }

 ---------------------------------------------------------------------
 指定短路器

 定义一个断路器类实现SponsorClient，实现对应方法
 @Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public BaseResult<List<AdPlan>> getAdPlans(AdPlanGetRquest request) {
        return new BaseResult<List<AdPlan>>(1, "断路器执行");
    }
}

* */

@Slf4j
@RestController
public class SearchController {

    private final RestTemplate restTemplate;

    private final ISearch iSearch;

    @Autowired
    public SearchController(RestTemplate restTemplate, ISearch iSearch) {
        this.restTemplate = restTemplate;
        this.iSearch = iSearch;
    }

    @Resource
    private SponsorClient sponsorClient;

    @SuppressWarnings("all")
    @IgnoreResponseAdvice
    @PostMapping("/getAdPlansBuRibbon")
    public BaseResult<List<AdPlan>> getAdPlanByRebbon(@RequestBody AdPlanGetRquest request){
        return restTemplate.postForEntity(
                "http://eureka-client-ad-sponsor/ad-sponsor/get/plan",
                request,
                BaseResult.class
        ).getBody();
    }


    @IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    public BaseResult<List<AdPlan>> getAdPlanByFeign(@RequestBody AdPlanGetRquest request){
        return sponsorClient.getAdPlans(request);
    }


    @PostMapping("/fetchAds")
    public SearchResponse fetchAds(@RequestBody SearchRequest request){
        return iSearch.fetch(request);
    }
}
