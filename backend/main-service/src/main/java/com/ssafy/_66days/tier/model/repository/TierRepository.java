package com.ssafy._66days.tier.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy._66days.tier.model.entity.Tier;

public interface TierRepository extends JpaRepository<Tier, Long> {
}
