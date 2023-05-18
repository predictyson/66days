package com.ssafy._66days.user.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy._66days.user.enums.Provider;
import com.ssafy._66days.user.model.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findById(UUID uuid);
	Optional<User> findByEmailAndProvider(String email, Provider provider);
}
