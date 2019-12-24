package com.tuanzhang.ad.dao;

import com.tuanzhang.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUnitReposity extends JpaRepository<AdUnit, Long> {
}
