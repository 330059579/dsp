package com.tuanzhang.ad.sender.index;

import com.alibaba.fastjson.JSON;
import com.tuanzhang.ad.dump.table.*;
import com.tuanzhang.ad.handler.AdLevelDataHandler;
import com.tuanzhang.ad.index.DataLevel;
import com.tuanzhang.ad.mysql.constant.Constant;
import com.tuanzhang.ad.mysql.dto.MySqlRowData;
import com.tuanzhang.ad.sender.ISender;
import com.tuanzhang.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("indexSender")
public class IndexSender implements ISender {


    @Override
    public void sender(MySqlRowData rowData) {


        Integer level = rowData.getLevel();
        if (DataLevel.LEVEL2.getLevel().equals(level)) {
            level2RowData(rowData);
        }else if (DataLevel.LEVEL3.getLevel().equals(level)) {
            Level3RowData(rowData);
        }else if (DataLevel.LEVEL4.getLevel().equals(level)) {
            Level4RowData(rowData);
        }else {
            log.error("mysqlRowData ERROR: {}", JSON.toJSONString(rowData));
        }

    }

    private void level2RowData(MySqlRowData rowData) {
        //计划表
        if (rowData.getTableName().equals(Constant.AD_PLAN_TABLE_INFO.TABLE_NAME)) {
            List<AdPlanTable> tableList = new ArrayList<>();
            for (Map<String, String> filedValueMap : rowData.getFiledValueMap()) {
                AdPlanTable adPlanTable = new AdPlanTable();
                filedValueMap.forEach((k, v) ->{
                    switch (k) {
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_ID:
                            adPlanTable.setPlanId(Long.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_USER_ID:
                            adPlanTable.setUserId(Long.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_PLAN_STATUS:
                            adPlanTable.setPlanStatus(Integer.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_START_DATE:
                            adPlanTable.setStartDate(CommonUtils.parseDate(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_END_DATE:
                            adPlanTable.setEndDate(CommonUtils.parseDate(v));
                            break;
                    }
                });

                tableList.add(adPlanTable);

                tableList.forEach(p -> AdLevelDataHandler.handleLevel2(p, rowData.getOpType()));
            }
        }else if (rowData.getTableName().equals(Constant.AD_CREATIVE_TABLE_INFO.TABLE_NAME)) {
            List<AdCreativeTable> tableList = new ArrayList<>();
            for (Map<String, String> filedValueMap : rowData.getFiledValueMap()) {
                AdCreativeTable adCreativeTable = new AdCreativeTable();
                filedValueMap.forEach((k, v) ->{
                    switch (k) {
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_ID:
                            adCreativeTable.setAdId(Long.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_TYPE:
                            adCreativeTable.setType(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_MATERIAL_TYPE:
                            adCreativeTable.setMaterialType(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_HEIGHT:
                            adCreativeTable.setHeigit(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_WIDTH:
                            adCreativeTable.setWidth(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_AUDIT_STATUS:
                            adCreativeTable.setAduiotStatus(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.URL:
                            adCreativeTable.setAdUrl(v);
                            break;
                    }
                });

                tableList.add(adCreativeTable);

                tableList.forEach(p -> AdLevelDataHandler.handleLevel2(p, rowData.getOpType()));
        }
    }else {
        }
    }


    private void Level3RowData(MySqlRowData rowData){
        if (rowData.getTableName().equals(Constant.AD_UNIT_TABLE_INFO.TABLE_NAME)) {
            List<AdUnitTable> adUnitTables = new ArrayList<>();
            for (Map<String, String> filedValueMap : rowData.getFiledValueMap()) {
                AdUnitTable unitTable = new AdUnitTable();
                filedValueMap.forEach((k,v) -> {
                    switch (k){
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_ID:
                            unitTable.setUnitId(Long.valueOf(v));
                            break;
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_UNIT_STATUS:
                            unitTable.setUnitStatus(Integer.valueOf(v));
                            break;
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_POSITION_TYPE:
                            unitTable.setPositionType(Integer.valueOf(v));
                            break;
                        case Constant.AD_UNIT_TABLE_INFO.COLUMN_PLAN_ID:
                            unitTable.setPlanId(Long.valueOf(v));
                            break;
                    }
                });

                adUnitTables.add(unitTable);
            }

            adUnitTables.forEach(u -> AdLevelDataHandler.handleLevel3(u, rowData.getOpType()));
        }else if (rowData.getTableName().equals(Constant.AD_CREATIVE_UNIT_TABLE_INFO.TABLE_NAME)) {
            List<AdCreativeUnitTable> adCreativeTables = new ArrayList<>();
            for (Map<String, String> filedValueMap : rowData.getFiledValueMap()) {
                AdCreativeUnitTable adCreativeTable = new AdCreativeUnitTable();
                filedValueMap.forEach((k,v) -> {
                    switch (k){
                        case Constant.AD_CREATIVE_UNIT_TABLE_INFO.COLUMN_CREATIVE_ID:
                            adCreativeTable.setAdId(Long.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_UNIT_TABLE_INFO.COLUMN_UNIT_ID:
                            adCreativeTable.setUnitId(Long.valueOf(v));
                            break;
                    }
                });

                adCreativeTables.add(adCreativeTable);
            }

            adCreativeTables.forEach(u -> AdLevelDataHandler.handleLevel3(u, rowData.getOpType()));
        }
    }


    private void Level4RowData(MySqlRowData rowData){
        switch (rowData.getTableName()) {
            case Constant.AD_UNIT_DISTRICT_TABLE_INFO.TABLE_NAME:
                List<AdUnitDistrictTable> districtTables = new ArrayList<>();
                for (Map<String, String> filedValueMap : rowData.getFiledValueMap()) {
                    AdUnitDistrictTable districtTable = new AdUnitDistrictTable();
                    filedValueMap.forEach((k, v) -> {
                        switch (k) {
                            case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_UNIT_ID:
                                districtTable.setUnitId(Long.valueOf(v));
                                break;
                            case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_PROVINCE:
                                districtTable.setProvince(v);
                                break;
                            case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_CITY:
                                districtTable.setCity(v);
                                break;
                        }
                    });
                    districtTables.add(districtTable);
                }

                districtTables.forEach(d -> AdLevelDataHandler.handleLevel4(d, rowData.getOpType()));
                break;
            case Constant.AD_UNIT_IT_TABLE_INFO.TABLE_NAME:
                List<AdUnitItTable> itTables = new ArrayList<>();
                for (Map<String, String> filedValueMap : rowData.getFiledValueMap()) {
                    AdUnitItTable itTable = new AdUnitItTable();
                    filedValueMap.forEach((k, v) -> {
                        switch (k) {
                            case Constant.AD_UNIT_IT_TABLE_INFO.COLUMN_UNIT_ID:
                                itTable.setUnitId(Long.valueOf(v));
                                break;
                            case Constant.AD_UNIT_IT_TABLE_INFO.COLUMN_IT_TAG:
                                itTable.setItTag(v);
                                break;
                        }
                    });
                    itTables.add(itTable);
                }

                itTables.forEach(d -> AdLevelDataHandler.handleLevel4(d, rowData.getOpType()));
                break;

            case Constant.AD_UNIT_KEYWORD_TABLE_INFO.TABLE_NAME:
                List<AdUnitKeywordTable> keywordTables = new ArrayList<>();
                for (Map<String, String> filedValueMap : rowData.getFiledValueMap()) {
                    AdUnitKeywordTable keywordTable = new AdUnitKeywordTable();
                    filedValueMap.forEach((k, v) -> {
                        switch (k) {
                            case Constant.AD_UNIT_KEYWORD_TABLE_INFO.COLUMN_UNIT_ID:
                                keywordTable.setUnitId(Long.valueOf(v));
                                break;
                            case Constant.AD_UNIT_KEYWORD_TABLE_INFO.COLUMN_KEYWORD:
                                keywordTable.setKeyword(v);
                                break;
                        }
                    });
                    keywordTables.add(keywordTable);
                }

                keywordTables.forEach(d -> AdLevelDataHandler.handleLevel4(d, rowData.getOpType()));
                break;
        }


    }

}
