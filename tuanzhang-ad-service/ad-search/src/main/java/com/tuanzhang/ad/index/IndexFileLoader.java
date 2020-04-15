package com.tuanzhang.ad.index;

import com.alibaba.fastjson.JSON;
import com.tuanzhang.ad.client.vo.AdPlan;
import com.tuanzhang.ad.dump.DConstant;
import com.tuanzhang.ad.dump.table.*;
import com.tuanzhang.ad.handler.AdLevelDataHandler;
import com.tuanzhang.ad.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
@DependsOn("dataTable")
public class IndexFileLoader {

    private List<String> loadDumpData(String fileName) {
        try(BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            return br.lines().collect(Collectors.toList());
        }catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }
    }


    @PostConstruct
    public void init(){
        List<String> adPlanStrings = loadDumpData(DConstant.DATA_ROOT_DIR + DConstant.AD_PLAN);
        adPlanStrings.forEach(p -> AdLevelDataHandler.handleLevel2(JSON.parseObject(p, AdPlanTable.class), OpType.ADD));


        List<String> adCreativeStings = loadDumpData(DConstant.DATA_ROOT_DIR + DConstant.AD_CREATIVE);
        adCreativeStings.forEach(p -> AdLevelDataHandler.handleLevel2(JSON.parseObject(p, AdCreativeTable.class), OpType.ADD));

        List<String> adUnitStings = loadDumpData(DConstant.DATA_ROOT_DIR + DConstant.AD_UNIT);
        adUnitStings.forEach(p -> AdLevelDataHandler.handleLevel3(JSON.parseObject(p, AdUnitTable.class), OpType.ADD));

        List<String> adCreativeUnitStrings = loadDumpData(DConstant.DATA_ROOT_DIR + DConstant.AD_CREATIVE_UNIT);
        adCreativeUnitStrings.forEach(p -> AdLevelDataHandler.handleLevel3(JSON.parseObject(p, AdCreativeUnitTable.class), OpType.ADD));

        List<String> adUnitDistrictStings = loadDumpData(DConstant.DATA_ROOT_DIR + DConstant.AD_UNIT_DISTRICT);
        adUnitDistrictStings.forEach(p -> AdLevelDataHandler.handleLevel4(JSON.parseObject(p, AdUnitDistrictTable.class), OpType.ADD));


        List<String> adUnitItStings = loadDumpData(DConstant.DATA_ROOT_DIR + DConstant.AD_UNIT_IT);
        adUnitItStings.forEach(p -> AdLevelDataHandler.handleLevel4(JSON.parseObject(p, AdUnitItTable.class), OpType.ADD));

        List<String> adUnitKeywordStings = loadDumpData(DConstant.DATA_ROOT_DIR + DConstant.AD_UNIT_KEYWORD);
        adUnitKeywordStings.forEach(p -> AdLevelDataHandler.handleLevel4(JSON.parseObject(p, AdUnitKeywordTable.class), OpType.ADD));
    }


    public static void main(String[] args) {
        String format = String.format("/aaa/", "bbb.txt");
        System.out.println(format);
    }

}
