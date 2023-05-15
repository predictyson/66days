package com.ssafy._66days.mainservice.user.model.dto;

import com.ssafy._66days.mainservice.user.model.entity.User;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private UUID userId;
    private String email;
    private String profileImagePath;
    private String nickname;
    private Long exp;
    private Long point;
    private String githubUrl;
    private String blogUrl;
    private Long animalId;
    private Long tierId;
    public static UserDTO of(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .profileImagePath(user.getProfileImagePath())
                .nickname(user.getNickname())
                .exp(user.getExp())
                .point(user.getPoint())
                .githubUrl(user.getGithubUrl())
                .blogUrl(user.getBlogUrl())
                .animalId(user.getAnimalId())
                .tierId(user.getTierId())
                .build();
    }
}
