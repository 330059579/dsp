package com.tuanzhang.ad.service.impl;

import com.tuanzhang.ad.dao.AdCreativeRepository;
import com.tuanzhang.ad.entity.AdCreative;
import com.tuanzhang.ad.service.AdCreativeService;
import com.tuanzhang.ad.vo.AdUnitCreativeRequest;
import com.tuanzhang.ad.vo.AdUnitCreativeResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("adCreativeService")
public class AdCreativeServiceImpl implements AdCreativeService {

    @Resource
    private AdCreativeRepository adCreativeRepository;

    @Override
    public AdUnitCreativeResponse createCreative(AdUnitCreativeRequest request) {
        AdCreative save = adCreativeRepository.save(request.convertCreative());
        return new AdUnitCreativeResponse(save.getId(), save.getName());
    }
}
