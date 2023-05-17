package com.ssafy._66days.mainservice.user.model.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy._66days.mainservice.user.model.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<UUID> findUserIdByNickname(String nickname);

	Optional<User> findByNickname(String userName);

	@Query(value = "SELECT u FROM User u ORDER BY u.exp DESC")
	List<User> findAllOrderByExp();
}
