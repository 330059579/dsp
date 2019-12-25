package com.tuanzhang.ad.dao;

import com.tuanzhang.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface AdUnitReposity extends JpaRepository<AdUnit, Long> {

    AdUnit findByPlanIdAndUnitName(Long planId, String name);
}
