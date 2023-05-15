package com.ssafy._66days.mainservice.challenge.model.dto;

import com.ssafy._66days.mainservice.challenge.model.entity.MyChallenge;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MyChallengeHistoryDTO {
    private String challengeName;
    private boolean isSuccess;

    public static MyChallengeHistoryDTO of(MyChallenge myChallenge, boolean isSuccess) {
        return MyChallengeHistoryDTO.builder()
                .challengeName(myChallenge.getChallengeName())
                .isSuccess(isSuccess)
                .build();
    }
}
