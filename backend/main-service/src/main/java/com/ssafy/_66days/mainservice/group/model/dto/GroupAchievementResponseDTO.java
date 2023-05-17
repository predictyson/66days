package com.ssafy._66days.mainservice.group.model.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GroupAchievementResponseDTO {
    private String imagePath;
    private String challengeName;
    private Long challengeId;
    private int achievementCount;
}
