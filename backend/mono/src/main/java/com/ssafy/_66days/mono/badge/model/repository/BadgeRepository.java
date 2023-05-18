package com.ssafy._66days.mono.badge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy._66days.mono.badge.model.entity.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
