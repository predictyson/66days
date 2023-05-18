package com.ssafy._66days.mainservice.page.model.dto;

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
    private String challengeName;
    private Date startAt;
    private boolean todayStreak;

    public static MainPageTodoResponseDTO of(MyChallenge myChallenge, boolean state) {
        Date startAt = java.sql.Timestamp.valueOf(myChallenge.getStartAt());
        return MainPageTodoResponseDTO.builder()
                .myChallengeId(myChallenge.getMyChallengeId())
                .challengeName(myChallenge.getChallengeName())
                .startAt(startAt)
                .todayStreak(state)
                .build();
    }
}
