package com.tuanzhang.ad.dao;

import com.tuanzhang.ad.entity.AdUnitKeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface AdUnitKeyWordRepository extends JpaRepository<AdUnitKeyWord, Long> {
}
