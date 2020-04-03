package com.tuanzhang.ad.sender.index;

import com.alibaba.fastjson.JSON;
import com.tuanzhang.ad.dump.table.AdCreativeTable;
import com.tuanzhang.ad.dump.table.AdPlanTable;
import com.tuanzhang.ad.handler.AdLevelDataHandler;
import com.tuanzhang.ad.index.DataLevel;
import com.tuanzhang.ad.mysql.constant.Constant;
import com.tuanzhang.ad.mysql.dto.MySqlRowData;
import com.tuanzhang.ad.sender.ISender;
import com.tuanzhang.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        }else if (DataLevel.LEVEL4.getLevel().equals(level)) {

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
}
