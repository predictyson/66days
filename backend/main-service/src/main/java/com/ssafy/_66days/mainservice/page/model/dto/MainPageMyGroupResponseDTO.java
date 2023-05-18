package com.ssafy._66days.mainservice.page.model.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MainPageMyGroupResponseDTO {
    private String profileImagePath;
    private List<String> challengeImagePaths;

    public static MainPageMyGroupResponseDTO of(String profileImagePath, List<String> challengeImagePaths) {
        return MainPageMyGroupResponseDTO.builder()
                .profileImagePath(profileImagePath)
                .challengeImagePaths(challengeImagePaths)
                .build();
    }

}
