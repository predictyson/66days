package com.ssafy._66days.article.model.service;

import com.ssafy._66days.article.model.entity.user.User;
import com.ssafy._66days.article.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service

public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UUID getUserUuidByNickname(String nickName) {
        Optional<UUID> optionalUUID = userRepository.findUserIdByNickName(nickName);
        return optionalUUID.orElseThrow(() -> new IllegalArgumentException("유저 uuid를 찾을 수 없습니다"));
    }
    public User getUserByUserUuid(UUID userId) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        return optionalUser.orElse(null);
    }
}