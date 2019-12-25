package com.tuanzhang.ad.controller;

import com.alibaba.fastjson.JSON;
import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.service.AdUserService;
import com.tuanzhang.ad.vo.CreateUserRequest;
import com.tuanzhang.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class UserOpController {

    @Resource
    private AdUserService adUserService;

    @PostMapping("/creative/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException {
        log.info("createUser -> {}", JSON.toJSONString(request));
        return adUserService.createUser(request);
    }
}
