package com.ssafy._66days.user.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy._66days.user.model.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
}
