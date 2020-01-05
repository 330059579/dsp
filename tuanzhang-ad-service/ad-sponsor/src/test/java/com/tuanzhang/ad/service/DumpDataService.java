package com.tuanzhang.ad.service;

import com.alibaba.fastjson.JSON;
import com.tuanzhang.ad.Application;
import com.tuanzhang.ad.constant.CommonStatus;
import com.tuanzhang.ad.dao.*;
import com.tuanzhang.ad.dump.DConstant;
import com.tuanzhang.ad.dump.table.*;
import com.tuanzhang.ad.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.sound.midi.Soundbank;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {

    @Autowired
    private AdPlanRepository planRepository;
    @Autowired
    private AdUnitReposity adUnitReposity;
    @Autowired
    private AdCreativeRepository adCreativeRepository;
    @Autowired
    private AdCreativeUnitRepository adCreativeUnitRepository;
    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;
    @Autowired
    private AdUnitItRepository adUnitItRepository;
    @Autowired
    private AdUnitKeyWordRepository adUnitKeyWordRepository;

    private void dumpAdPlanTable(String fileName){
        List<AdPlan> list = planRepository.findAllByPlanStatus(CommonStatus.VALID.getStatus());
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        List<AdPlanTable> planTables = new ArrayList<>();
        list.forEach(p -> planTables.add(new AdPlanTable(p.getId(),
                p.getUserId(),p.getPlanStatus(),
                p.getStartDate(),p.getEndDate())));

        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
          for (AdPlanTable adPlanTable : planTables) {
              writer.write(JSON.toJSONString(adPlanTable));
              writer.newLine();
          }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void dumpAdUnitTable(String fileName){
        List<AdUnit> list = adUnitReposity.findAllByUnitStatus(CommonStatus.VALID.getStatus());
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        List<AdUnitTable> unitTables = new ArrayList<>();
        list.forEach(p -> unitTables.add(new AdUnitTable(p.getId(),
                p.getUnitStatus(),p.getPositionType(),
                p.getPlanId())));

        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitTable adUnitTable : unitTables) {
                writer.write(JSON.toJSONString(adUnitTable));
                writer.newLine();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void dumpAdCreativeTable(String fileName){
        List<AdCreative> list = adCreativeRepository.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        List<AdCreativeTable> creativeTables = new ArrayList<>();
        list.forEach(p -> creativeTables.add(new AdCreativeTable(p.getId(),
                p.getName(),p.getType(),
                p.getMaterialType(),p.getHeight(), p.getWidth(), p.getAuditStatus(), p.getUrl())));

        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdCreativeTable adCreativeTable : creativeTables) {
                writer.write(JSON.toJSONString(adCreativeTable));
                writer.newLine();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void dumpAdDistrictTable(String fileName){
        List<AdUnitDistrict> list = adUnitDistrictRepository.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        List<AdUnitDistrictTable> adUnitDistrictTables = new ArrayList<>();
        list.forEach(p -> adUnitDistrictTables.add(new AdUnitDistrictTable(p.getUnitId(),
              p.getProvice(), p.getCity())));

        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitDistrictTable adUnitDistrictTable : adUnitDistrictTables) {
                writer.write(JSON.toJSONString(adUnitDistrictTable));
                writer.newLine();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void dumpAdCreativeUnitTable(String fileName){
        List<AdCreativeUnit> list = adCreativeUnitRepository.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        List<AdCreativeUnitTable> creativeUnitTables = new ArrayList<>();
        list.forEach(p -> creativeUnitTables.add(new AdCreativeUnitTable(p.getCreativeId(),
                p.getUnitId())));

        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdCreativeUnitTable adCreativeUnitTable : creativeUnitTables) {
                writer.write(JSON.toJSONString(adCreativeUnitTable));
                writer.newLine();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void dumpAdUnitItTable(String fileName){
        List<AdUnitIt> list = adUnitItRepository.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        List<AdUnitItTable> adUnitItTables = new ArrayList<>();
        list.forEach(p -> adUnitItTables.add(new AdUnitItTable(p.getUnitId(),
                p.getItTag())));

        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitItTable adUnitItTable : adUnitItTables) {
                writer.write(JSON.toJSONString(adUnitItTable));
                writer.newLine();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void dumpAdUnitKeywordTable(String fileName){
        List<AdUnitKeyWord> list = adUnitKeyWordRepository.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        List<AdUnitKeywordTable> adUnitItTables = new ArrayList<>();
        list.forEach(p -> adUnitItTables.add(new AdUnitKeywordTable(p.getUnitId(),
                p.getKeyWord())));

        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitKeywordTable adUnitItTable : adUnitItTables) {
                writer.write(JSON.toJSONString(adUnitItTable));
                writer.newLine();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Test
    public void dumpAdTableData(){
        dumpAdPlanTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_PLAN));

        dumpAdUnitTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_UNIT));

        dumpAdCreativeTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_CREATIVE));

        dumpAdCreativeUnitTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_CREATIVE_UNIT));

        dumpAdDistrictTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_UNIT_DISTRICT));

        dumpAdUnitItTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_UNIT_IT));

        dumpAdUnitKeywordTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,DConstant.AD_UNIT_KEYWORD));
    }
}
