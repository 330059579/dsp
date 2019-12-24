package com.tuanzhang.ad.dao;

import com.tuanzhang.ad.constant.CreativeType;
import com.tuanzhang.ad.entity.AdCreative;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdCreativeRepository extends JpaRepository<AdCreative, Long> {
}
