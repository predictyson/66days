package com.ssafy._66days.Article.model.service;

import com.ssafy._66days.Article.model.entity.user.User;
import com.ssafy._66days.Article.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service

public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUserUuid(UUID userUuid) {
        Optional<User> optionalUser = userRepository.findByUserUuid(userUuid);
        return optionalUser.orElse(null);
    }
}