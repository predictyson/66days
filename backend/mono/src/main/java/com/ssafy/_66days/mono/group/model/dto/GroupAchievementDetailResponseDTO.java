package com.ssafy._66days.mono.group.model.dto;

import com.ssafy._66days.mono.challenge.model.entity.GroupChallenge;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GroupAchievementDetailResponseDTO {
    private String challengeName;
    private Date startAt;
    private Date endAt;
    private String state;

    public static GroupAchievementDetailResponseDTO of(GroupChallenge groupChallenge) {
        Date startAt = java.sql.Timestamp.valueOf(groupChallenge.getStartAt());
        Date endAt = java.sql.Timestamp.valueOf(groupChallenge.getEndAt());
        return GroupAchievementDetailResponseDTO.builder()
                .challengeName(groupChallenge.getChallengeName())
                .startAt(startAt)
                .endAt(endAt)
                .state(groupChallenge.getState())
                .build();
    }
}
