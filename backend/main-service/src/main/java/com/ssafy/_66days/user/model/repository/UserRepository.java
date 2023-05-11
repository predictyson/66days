package com.ssafy._66days.user.model.repository;


import com.ssafy._66days.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UUID> findUserIdByNickname(String nickname);
    Optional<User> findByNickname(String userName);
}
