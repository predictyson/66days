package com.ssafy._66days.mono.user.model.dto;

import com.ssafy._66days.mono.user.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserGroupSearchPageResponseDTO {
    private UUID userId;
    private String image;
    private String name;

    public static UserGroupSearchPageResponseDTO of(User user){
        return UserGroupSearchPageResponseDTO.builder()
                .userId(user.getUserId())
                .image(user.getProfileImagePath())
                .name(user.getNickname())
                .build();
    }
}
