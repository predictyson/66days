package com.ssafy._66days.article.model.repository;


import com.ssafy._66days.article.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserId(UUID userId);

//    @Query("SELECT u.auth FROM User u WHERE u.nickname = :nickname")
    Optional<UUID> findUserIdByNickName(String nickName);


}
