package com.tuanzhang.ad.handler;

import com.tuanzhang.ad.dump.table.*;
import com.tuanzhang.ad.index.DataTable;
import com.tuanzhang.ad.index.IndexAware;
import com.tuanzhang.ad.index.adPlan.AdPlanIndex;
import com.tuanzhang.ad.index.adPlan.AdPlanObject;
import com.tuanzhang.ad.index.adunit.AdUnitIndex;
import com.tuanzhang.ad.index.adunit.AdUnitObject;
import com.tuanzhang.ad.index.creative.CreativeIndex;
import com.tuanzhang.ad.index.creative.CreativeObject;
import com.tuanzhang.ad.index.creativeunit.CreativeUnitIndex;
import com.tuanzhang.ad.index.creativeunit.CreativeUnitObject;
import com.tuanzhang.ad.index.district.UnitDistrictIndex;
import com.tuanzhang.ad.mysql.constant.OpType;
import com.tuanzhang.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//索引之间存在层级划分：也就是依赖关系的划分
@Slf4j
public class AdLevelDataHandler {

    private static <K,V> void handleBinLogEvent(IndexAware<K, V> index, K key, V  value, OpType type){

        switch (type) {
            case ADD:
                index.add(key, value);
                break;
            case UPDATE:
                index.update(key, value);
                break;
            case DELETE:
                index.delete(key, value);
                break;
            default:
                break;
        }
    }

    public static  void  handleLevel2(AdPlanTable adPlanTable, OpType type) {
        AdPlanObject planObject = new AdPlanObject();
        BeanUtils.copyProperties(adPlanTable, planObject);
        handleBinLogEvent(DataTable.of(AdPlanIndex.class), planObject.getPlanId(),planObject, type);
    }


    public static  void  handleLevel2(AdCreativeTable creativeTable, OpType type) {
        CreativeObject creativeObject = new CreativeObject();
        BeanUtils.copyProperties(creativeTable, creativeObject);
        handleBinLogEvent(DataTable.of(CreativeIndex.class), creativeObject.getAdId(),creativeObject, type);
    }

    public static  void  handleLevel3(AdUnitTable adUnitTable, OpType type) {
        if (type == OpType.UPDATE) {
            return;
        }

        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(adUnitTable.getPlanId());
        if (null == adPlanObject) {
            return;
        }

        AdUnitObject unitObject = new AdUnitObject(adUnitTable.getUnitId(), adUnitTable.getUnitStatus(),
                adUnitTable.getPositionType(), adUnitTable.getPlanId(), adPlanObject);

        handleBinLogEvent(DataTable.of(AdUnitIndex.class), unitObject.getUnitId(),unitObject, type);
    }


    public static  void  handleLevel3(AdCreativeUnitTable adCreativeUnitTable, OpType type) {
        if (type == OpType.UPDATE) {
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adCreativeUnitTable.getUnitId());
        if (null == adUnitObject) {
            return;
        }

        CreativeObject creativeObject = DataTable.of(CreativeIndex.class).get(adCreativeUnitTable.getAdId());
        if (null == creativeObject) {
            return;
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(adCreativeUnitTable.getAdId(), adCreativeUnitTable.getUnitId());
        handleBinLogEvent(DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(creativeObject.getAdId().toString(), creativeUnitObject.getUnitId().toString()),
                creativeUnitObject, type);
    }


    public static  void  handleLevel4(AdUnitDistrictTable adUnitDistrictTable, OpType type) {
        if (type == OpType.UPDATE) {
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitDistrictTable.getUnitId());
        if (null == adUnitObject) {
            return;
        }

        String key = CommonUtils.stringConcat(adUnitDistrictTable.getProvince(), adUnitDistrictTable.getCity());
        Set<Long> value = new HashSet<>(Collections.singleton(adUnitDistrictTable.getUnitId()));
        handleBinLogEvent(DataTable.of(UnitDistrictIndex.class),
               key, value, type);
    }


    public static  void  handleLevel4(AdUnitItTable adUnitItTable, OpType type) {
        if (type == OpType.UPDATE) {
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitItTable.getUnitId());
        if (null == adUnitObject) {
            return;
        }

        Set<Long> value = new HashSet<>(Collections.singleton(adUnitItTable.getUnitId()));
        handleBinLogEvent(DataTable.of(UnitDistrictIndex.class),
                adUnitItTable.getItTag(), value, type);
    }


    public static  void  handleLevel4(AdUnitKeywordTable adUnitKeywordTable, OpType type) {
        if (type == OpType.UPDATE) {
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitKeywordTable.getUnitId());
        if (null == adUnitObject) {
            return;
        }

        Set<Long> value = new HashSet<>(Collections.singleton(adUnitKeywordTable.getUnitId()));
        handleBinLogEvent(DataTable.of(UnitDistrictIndex.class),
                adUnitKeywordTable.getKeyword(), value, type);
    }
}
