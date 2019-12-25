package com.tuanzhang.ad.controller;

import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.service.AdCreativeService;
import com.tuanzhang.ad.vo.AdUnitCreativeRequest;
import com.tuanzhang.ad.vo.AdUnitCreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class CreativeOpController {

    @Resource
    private AdCreativeService adCreativeService;

    @PostMapping("/create/creative")
    public AdUnitCreativeResponse creativeCreative(@RequestBody AdUnitCreativeRequest request) throws AdException {
        return adCreativeService.createCreative(request);
    }
}
