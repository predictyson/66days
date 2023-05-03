package com.ssafy._66days.Article.model.repository;


import com.ssafy._66days.Article.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserUuid(UUID userUuid);

}
