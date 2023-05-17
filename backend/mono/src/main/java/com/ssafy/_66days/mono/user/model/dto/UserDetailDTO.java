package com.ssafy._66days.mono.user.model.dto;

import com.ssafy._66days.mono.animal.model.dto.AnimalDTO;
import com.ssafy._66days.mono.tier.model.dto.TierDTO;
import com.ssafy._66days.mono.user.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UserDetailDTO {
    private UUID userId;
    private String email;
    private String profileImagePath;
    private String nickname;
    private Long exp;
    private Long point;
    private String githubUrl;
    private String blogUrl;
    private AnimalDTO animalDTO;
    private TierDTO tierDTO;
    public static UserDetailDTO of(User user, AnimalDTO animalDTO, TierDTO tierDTO) {
        return UserDetailDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .profileImagePath(user.getProfileImagePath())
                .nickname(user.getNickname())
                .exp(user.getExp())
                .point(user.getPoint())
                .githubUrl(user.getGithubUrl())
                .blogUrl(user.getBlogUrl())
                .animalDTO(animalDTO)
                .tierDTO(tierDTO)
                .build();
    }
}
