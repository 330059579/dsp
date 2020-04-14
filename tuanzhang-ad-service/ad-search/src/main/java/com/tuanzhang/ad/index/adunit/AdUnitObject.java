package com.tuanzhang.ad.index.adunit;

import com.tuanzhang.ad.index.adPlan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {

    private Long unitId;

    private Integer unitStatus;

    private Integer posoyionType;

    private Long planId;

    private AdPlanObject adPlanObject;

    void update(AdUnitObject newObject) {
        if (null != newObject.getUnitId()) {
            this.unitId = newObject.getUnitId();
        }

        if (null != newObject.getUnitStatus()) {
            this.unitStatus = newObject.getUnitStatus();
        }

        if (null != newObject.getPosoyionType()) {
            this.posoyionType = newObject.getPosoyionType();
        }

        if (null != newObject.getPlanId()) {
            this.planId = newObject.getPlanId();
        }

        if (null != newObject.getAdPlanObject()) {
            this.adPlanObject = newObject.getAdPlanObject();
        }
    }

    private static boolean isKaiPing(int positionType) {
        return (positionType & AdUnitConstants.POSITIONTYPE.KAIPING) >0;
    }


    private static boolean isTiePian(int positionType) {
        return (positionType & AdUnitConstants.POSITIONTYPE.TIEPIAN) >0;
    }

    private static boolean isTiePianMiddle(int positionType) {
        return (positionType & AdUnitConstants.POSITIONTYPE.TIEPIAN_MIDDLE) >0;
    }

    private static boolean isTiePianPause(int positionType) {
        return (positionType & AdUnitConstants.POSITIONTYPE.TIEPIAN_PAUSE) >0;
    }

    private static boolean isTiePianPost(int positionType) {
        return (positionType & AdUnitConstants.POSITIONTYPE.TIEPIAN_POST) >0;
    }


    public static boolean isAdSlotTypeOK(int adSlotType, int positionType) {
        switch (adSlotType) {
            case AdUnitConstants.POSITIONTYPE.KAIPING:
                return isKaiPing(positionType);
            case AdUnitConstants.POSITIONTYPE.TIEPIAN:
                return isTiePian(positionType);
            case AdUnitConstants.POSITIONTYPE.TIEPIAN_MIDDLE:
                return isTiePianMiddle(positionType);
            case AdUnitConstants.POSITIONTYPE.TIEPIAN_PAUSE:
                return isTiePianPause(positionType);
            case AdUnitConstants.POSITIONTYPE.TIEPIAN_POST:
                return isTiePianPost(positionType);
            default:
                    return false;
        }
    }
}
