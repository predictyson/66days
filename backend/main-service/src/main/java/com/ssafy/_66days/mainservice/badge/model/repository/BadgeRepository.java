package com.ssafy._66days.mainservice.badge.model.repository;

import com.ssafy._66days.mainservice.badge.model.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
