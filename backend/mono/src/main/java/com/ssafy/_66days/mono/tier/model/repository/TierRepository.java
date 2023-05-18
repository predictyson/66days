package com.ssafy._66days.mono.tier.model.repository;

import java.util.Optional;

import com.ssafy._66days.mono.tier.model.entity.Tier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TierRepository extends JpaRepository<Tier, Long> {

	Optional<Tier> findByTierId(Long tierId);
}
