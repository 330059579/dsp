package com.tuanzhang.ad.controller;


import com.tuanzhang.ad.entity.AdPlan;
import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.service.AdPlanService;
import com.tuanzhang.ad.vo.AdPlanGetRequest;
import com.tuanzhang.ad.vo.AdPlanReponse;
import com.tuanzhang.ad.vo.AdPlanRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class AdPlanOpController {

    @Resource
    private AdPlanService adPlanService;

    @PostMapping("/create/plan")
    public AdPlanReponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        return adPlanService.creativeAdPlan(request);
    }


    @PostMapping("/test/aa")
    public AdPlanReponse testAA() throws AdException {
        System.out.println("hello world");
        return new AdPlanReponse(10L, "test");
    }

    @PostMapping("/get/plan")
    public List<AdPlan> getPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException {
        return adPlanService.getAdPlanByIds(request);
    }


    @PutMapping("/update/plan")
    public AdPlanReponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        return adPlanService.updateAdPlan(request);
    }


    @DeleteMapping("/delete/plan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        adPlanService.deteteAdPlan(request);
    }
}
