package com.tuanzhang.ad.client;

import com.tuanzhang.ad.client.vo.AdPlan;
import com.tuanzhang.ad.client.vo.AdPlanGetRquest;
import com.tuanzhang.ad.vo.BaseResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public BaseResult<List<AdPlan>> getAdPlans(AdPlanGetRquest request) {
        return new BaseResult<List<AdPlan>>(1, "断路器执行");
    }
}
