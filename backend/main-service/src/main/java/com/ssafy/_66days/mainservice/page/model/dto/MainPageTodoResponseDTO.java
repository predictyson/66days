package com.ssafy._66days.mainservice.page.model.dto;

import com.ssafy._66days.mainservice.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mainservice.challenge.model.entity.MyChallenge;
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
    private String challengeName;
    private Date startAt;
    private boolean todayStreak;

    public static MainPageTodoResponseDTO my(MyChallenge myChallenge, boolean state) {
        Date startAt = java.sql.Timestamp.valueOf(myChallenge.getStartAt());
        return MainPageTodoResponseDTO.builder()
                .myChallengeId(myChallenge.getMyChallengeId())
                .challengeName(myChallenge.getChallengeName())
                .startAt(startAt)
                .todayStreak(state)
                .build();
    }
    public static MainPageTodoResponseDTO group(GroupChallenge groupChallenge, boolean state) {
        Date startAt = java.sql.Timestamp.valueOf(groupChallenge.getStartAt());
        return MainPageTodoResponseDTO.builder()
                .groupChallengeId(groupChallenge.getGroupChallengeId())
                .challengeName(groupChallenge.getChallengeName())
                .startAt(startAt)
                .todayStreak(state)
                .build();
    }
}
