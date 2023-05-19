package com.ssafy._66days.mono.badge.model.repository;

import com.ssafy._66days.mono.badge.model.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
