package com.tuanzhang.ad.client;

import com.tuanzhang.ad.client.vo.AdPlan;
import com.tuanzhang.ad.client.vo.AdPlanGetRquest;
import com.tuanzhang.ad.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClientHystrix.class) //服务降级，指定断路器
public interface SponsorClient {

    //定义一个接口就可以使用feign调用
    @RequestMapping(value = "/ad-sponsor/get/plan", method = RequestMethod.POST)
    BaseResult<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRquest request);
}
