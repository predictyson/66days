package com.ssafy._66days.user.model.repository;


import com.ssafy._66days.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

//    @Query("SELECT u.auth FROM User u WHERE u.nickname = :nickname")
    Optional<UUID> findUserIdByNickname(String nickname);


}
