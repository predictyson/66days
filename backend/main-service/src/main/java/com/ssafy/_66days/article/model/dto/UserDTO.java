package com.ssafy._66days.article.model.dto;

import com.ssafy._66days.article.model.entity.Animal;
import com.ssafy._66days.article.model.entity.user.User;
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
    private String nickName;
    private Long exp;
    private Long point;
    private String githubUrl;
    private String blogUrl;
    private Animal animalId;
    public static UserDTO of(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .profileImagePath(user.getProfileImagePath())
                .nickName(user.getNickName())
                .exp(user.getExp())
                .point(user.getPoint())
                .githubUrl(user.getGithubUrl())
                .blogUrl(user.getBlogUrl())
                .animalId(user.getAnimalId())
                .build();
    }
}
