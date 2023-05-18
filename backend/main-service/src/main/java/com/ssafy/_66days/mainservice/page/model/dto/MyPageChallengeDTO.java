package com.ssafy._66days.mainservice.page.model.dto;

import com.ssafy._66days.mainservice.challenge.model.entity.MyChallenge;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MyPageChallengeDTO {
    private Long myChallengeId;
    private String name;
    private String category;
    private Date startAt;

    public static MyPageChallengeDTO of(MyChallenge myChallenge) {
        Date startAt = java.sql.Timestamp.valueOf(myChallenge.getStartAt());
        return MyPageChallengeDTO.builder()
                .myChallengeId(myChallenge.getMyChallengeId())
                .name(myChallenge.getChallengeName())
                .category(myChallenge.getChallenge().getTopic())
                .startAt(startAt)
                .build();
    }
}
