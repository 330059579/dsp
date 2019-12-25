package com.tuanzhang.ad.service;

import com.tuanzhang.ad.vo.AdUnitCreativeRequest;
import com.tuanzhang.ad.vo.AdUnitCreativeResponse;

public interface AdCreativeService {

    AdUnitCreativeResponse createCreative(AdUnitCreativeRequest request);
}
