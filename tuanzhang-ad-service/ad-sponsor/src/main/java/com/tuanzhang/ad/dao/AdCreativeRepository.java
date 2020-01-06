package com.tuanzhang.ad.dao;

import com.tuanzhang.ad.entity.AdCreative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AdCreativeRepository extends JpaRepository<AdCreative, Long> {

}
