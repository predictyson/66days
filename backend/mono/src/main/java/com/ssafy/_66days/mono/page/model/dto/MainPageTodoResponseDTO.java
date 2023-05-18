package com.ssafy._66days.mono.page.model.dto;

import com.ssafy._66days.mono.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mono.challenge.model.entity.MyChallenge;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MainPageTodoResponseDTO {
    private Long myChallengeId;
    private Long groupChallengeId;
    private Long groupId;
    private String challengeName;
    private String imagePath;
    private Date startAt;
    private boolean todayStreak;

    public static MainPageTodoResponseDTO my(MyChallenge myChallenge, boolean state) {
        Date startAt = java.sql.Timestamp.valueOf(myChallenge.getStartAt());
        return MainPageTodoResponseDTO.builder()
                .myChallengeId(myChallenge.getMyChallengeId())
                .challengeName(myChallenge.getChallengeName())
                .imagePath(myChallenge.getChallenge().getBadgeImage())
                .startAt(startAt)
                .todayStreak(state)
                .build();
    }
    public static MainPageTodoResponseDTO group(GroupChallenge groupChallenge, boolean state) {
        Date startAt = java.sql.Timestamp.valueOf(groupChallenge.getStartAt());
        return MainPageTodoResponseDTO.builder()
                .groupChallengeId(groupChallenge.getGroupChallengeId())
                .groupId(groupChallenge.getGroup().getGroupId())
                .challengeName(groupChallenge.getChallengeName())
                .imagePath(groupChallenge.getChallenge().getBadgeImage())
                .startAt(startAt)
                .todayStreak(state)
                .build();
    }
}
