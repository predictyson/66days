package com.ssafy._66days.mono.tier.model.repository;

import com.ssafy._66days.mono.tier.model.entity.Tier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TierRepository extends JpaRepository<Tier, Long> {

	Optional<Tier> findByTierId(Long tierId);
}
