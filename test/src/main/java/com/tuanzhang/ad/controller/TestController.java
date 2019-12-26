package com.tuanzhang.ad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {


    @GetMapping("/test/aaa")
    public String SmsSend(Map<String, Object> model) {
        return "sms_batch";
    }
}
