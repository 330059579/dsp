package com.tuanzhang.ad.service;

import com.tuanzhang.ad.entity.AdUnitDistrict;
import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.vo.*;

public interface AdUnitService {

    AdUnitResponse createAdUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createAdUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createAdUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createAdUnitDistrict(AdUnitDistrictRequest request) throws AdException;

    CreativeUnitResponse creativeCreativeUnit(CreativeUnitRequest request) throws AdException;

}
