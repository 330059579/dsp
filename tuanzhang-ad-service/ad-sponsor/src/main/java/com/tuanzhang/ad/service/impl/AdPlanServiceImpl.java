package com.tuanzhang.ad.service.impl;

import com.tuanzhang.ad.constant.CommonStatus;
import com.tuanzhang.ad.dao.AdPlanRepository;
import com.tuanzhang.ad.dao.AdUserRepository;
import com.tuanzhang.ad.entity.AdPlan;
import com.tuanzhang.ad.entity.AdUser;
import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.service.AdPlanService;
import com.tuanzhang.ad.utils.DateUtils;
import com.tuanzhang.ad.vo.AdPlanGetRequest;
import com.tuanzhang.ad.vo.AdPlanReponse;
import com.tuanzhang.ad.vo.AdPlanRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("adPlanService")
public class AdPlanServiceImpl implements AdPlanService {

    @Resource
    private AdPlanRepository  adPlanRepository;
    @Resource
    private AdUserRepository  adUserRepository;

    @Override
    @Transactional
    public AdPlanReponse creativeAdPlan(AdPlanRequest request) throws AdException {
        if (!request.checkParams()){
            throw new AdException("参数为空！");
        }

        Optional<AdUser> adUser = adUserRepository.findById(request.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException("用户不存在！");
        }


        AdPlan adPlan = adPlanRepository.save(new AdPlan(request.getUserId(),
                request.getPlanName(), DateUtils.parseDate(request.getStartDate()),
                DateUtils.parseDate(request.getEndDate())));

        return new AdPlanReponse(adPlan.getId(), adPlan.getPlanName());
    }

    @Override
    @Transactional
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if (CollectionUtils.isEmpty(request.getIds())) {
            throw new AdException("参数为空");
        }
        return adPlanRepository.findAllByIdInAndUserId(request.getIds(), request.getUserId());
    }

    @Override
    @Transactional
    public AdPlanReponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (! request.checkParams()) {
            throw new AdException("参数为空");
        }

        AdPlan adplan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (null == adplan) {
            throw new AdException("计划不存在！");
        }

        if (StringUtils.isEmpty(request.getPlanName())) {
            adplan.setPlanName(request.getPlanName());
        }

        adplan.setStartDate(DateUtils.parseDate(request.getStartDate()));
        adplan.setEndDate(DateUtils.parseDate(request.getEndDate()));

        AdPlan plan = adPlanRepository.save(adplan);

        return new AdPlanReponse(plan.getId(), plan.getPlanName());
    }

    @Override
    @Transactional
    public void deteteAdPlan(AdPlanRequest request) throws AdException {
        if (null == request.getId()) {
            throw new AdException("参数为空");
        }

        AdPlan adplan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (null == adplan) {
            throw new AdException("计划不存在！");
        }

        adplan.setPlanStatus(CommonStatus.INVALID.getStatus());
        adplan.setUpdateDate(new Date());
        adPlanRepository.save(adplan);
    }
}
