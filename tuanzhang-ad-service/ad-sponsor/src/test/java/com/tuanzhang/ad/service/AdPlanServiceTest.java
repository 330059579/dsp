package com.tuanzhang.ad.service;

import com.tuanzhang.ad.Application;
import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.vo.AdPlanGetRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdPlanServiceTest {

    @Autowired
    private AdPlanService adPlanService;

    @Test
    public void testGetAdPlan() throws AdException{
        System.out.println(adPlanService.getAdPlanByIds(new AdPlanGetRequest(15L, Collections.singletonList(10L))));
    }
}
