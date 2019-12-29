package com.tuanzhang.ad.service;

import com.tuanzhang.ad.entity.AdPlan;
import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.vo.AdPlanGetRequest;
import com.tuanzhang.ad.vo.AdPlanReponse;
import com.tuanzhang.ad.vo.AdPlanRequest;

import java.util.List;

public interface AdPlanService {

    AdPlanReponse creativeAdPlan(AdPlanRequest request) throws AdException;

    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    AdPlanReponse updateAdPlan(AdPlanRequest request) throws AdException;

    void deteteAdPlan(AdPlanRequest request) throws AdException;

    AdPlan getInfo(Long i);
}
