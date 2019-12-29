package com.tuanzhang.ad.controller;


import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.service.AdUnitService;
import com.tuanzhang.ad.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class AdUnitOpController {

    @Resource
    private AdUnitService adUnitService;

    @PostMapping("/create/unit")
    public AdUnitResponse createUnit(@RequestBody AdUnitRequest request) throws AdException {
        return adUnitService.createAdUnit(request);
    }

    @PostMapping("/create/keyword")
    public AdUnitKeywordResponse creativeKeyword(@RequestBody AdUnitKeywordRequest request) throws  AdException{
        return adUnitService.createAdUnitKeyword(request);
    }

    @PostMapping("/create/it")
    public AdUnitItResponse creativeIt(@RequestBody AdUnitItRequest request) throws  AdException{
        return adUnitService.createAdUnitIt(request);
    }

    @PostMapping("/create/district")
    public AdUnitDistrictResponse creativeDistrict(@RequestBody AdUnitDistrictRequest request) throws  AdException{
        return adUnitService.createAdUnitDistrict(request);
    }

    @PostMapping("/create/creative/unit")
    public CreativeUnitResponse creativeCreaticeUnit(@RequestBody CreativeUnitRequest request) throws  AdException{
        return adUnitService.creativeCreativeUnit(request);
    }
}
