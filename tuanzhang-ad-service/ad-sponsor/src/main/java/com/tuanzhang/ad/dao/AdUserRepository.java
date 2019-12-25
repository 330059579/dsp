package com.tuanzhang.ad.dao;

import com.tuanzhang.ad.entity.AdPlan;
import com.tuanzhang.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    AdUser findByUsername(String userName);
}

