package com.tuanzhang.ad.dao;

import com.tuanzhang.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {

    AdPlan findByIdAndUserId(Long id, Long userId);

    List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);

    List<AdPlan> findAllByPlanStatus(Integer status);


}
