package com.tuanzhang.ad.service.impl;

import com.tuanzhang.ad.dao.*;
import com.tuanzhang.ad.entity.*;
import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.service.AdUnitService;
import com.tuanzhang.ad.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("adUnitService")
public class AdUnitServiceImpl implements AdUnitService {

    @Resource
    private AdUnitReposity adUnitReposity;
    @Resource
    private AdPlanRepository adPlanRepository;
    @Resource
    private AdUnitKeyWordRepository adUnitKeyWordRepository;
    @Resource
    private AdUnitDistrictRepository adUnitDistrictRepository;
    @Resource
    private AdUnitItRepository adUnitItRepository;
    @Resource
    private AdCreativeUnitRepository adCreativeUnitRepository;

    @Override
    @Transactional
    public AdUnitResponse createAdUnit(AdUnitRequest request) throws AdException {
        if (request.checkParams()) {
            throw new AdException("参数为空！");
        }

        Optional<AdPlan> adPlan = adPlanRepository.findById(request.getPlanId());
        if (adPlan.isPresent()) {
            throw  new AdException("营销计划不存在！");
        }

        AdUnit adUnit = adUnitReposity.findByPlanIdAndUnitName(adPlan.get().getId(), request.getUnitName());
        if (null != adUnit) {
            throw new AdException("推广计划已存在！");
        }

        AdUnit save = adUnitReposity.save(new AdUnit(request.getPlanId(), request.getUnitName(),
                request.getPositionType(), request.getBudget()));

        return new AdUnitResponse(save.getId(), save.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createAdUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        List<AdUnitKeywordRequest.UnitkeyWord> list = request.getUnitkeyWords();
        if (CollectionUtils.isEmpty(list)) {
            throw new AdException("参数为空!");
        }

        List<Long> ids = new ArrayList<>();
        list.forEach(unitkeyWord -> {
            AdUnitKeyWord save = adUnitKeyWordRepository.save(new AdUnitKeyWord(unitkeyWord.getUnitId(),
                    unitkeyWord.getKeyWorld()));
            ids.add(save.getId());
        });
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createAdUnitIt(AdUnitItRequest request) throws AdException {
        List<AdUnitItRequest.UnitIt> list = request.getUnitIts();
        if (CollectionUtils.isEmpty(list)) {
            throw new AdException("参数为空!");
        }

        List<Long> ids = new ArrayList<>();
        list.forEach(unitIt -> {
            AdUnitIt save = adUnitItRepository.save(new AdUnitIt(unitIt.getUnitId(),
                    unitIt.getIt()));
            ids.add(save.getId());
        });
        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createAdUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<AdUnitDistrictRequest.UnitDistrict> list = request.getUnitDistricts();
        if (CollectionUtils.isEmpty(list)) {
            throw new AdException("参数为空!");
        }

        List<Long> ids = new ArrayList<>();
        list.forEach(unitIt -> {
            AdUnitDistrict save = adUnitDistrictRepository.save(new AdUnitDistrict(unitIt.getUnitId(),
                    unitIt.getProvince(), unitIt.getCity()));
            ids.add(save.getId());
        });
        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public CreativeUnitResponse creativeCreativeUnit(CreativeUnitRequest request) throws AdException {
        List<Long> creativeIdList = request.getList().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getCreativeId).collect(Collectors.toList());

        List<Long> unitList = request.getList().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(creativeIdList) || CollectionUtils.isEmpty(unitList)) {
            throw new AdException("数据不存在！");
        }

        List<AdCreativeUnit> creativeUnits = new ArrayList<>();
        request.getList().forEach(i -> creativeUnits.add(new AdCreativeUnit(i.getCreativeId(), i.getUnitId())));
        List<Long> collect = adCreativeUnitRepository.saveAll(creativeUnits).stream()
                .map(AdCreativeUnit::getId).collect(Collectors.toList());
        return new CreativeUnitResponse(collect);
    }
}
