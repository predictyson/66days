package com.ssafy._66days.mono.challenge.model.dto.responseDTO;

import com.ssafy._66days.mono.challenge.model.entity.MyChallenge;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MyChallengeResponseDTO {
    private Long myChallengeId;
    private String imagePath;
    private String challengeName;
    private String content;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public static MyChallengeResponseDTO of(MyChallenge myChallenge) {
        return MyChallengeResponseDTO.builder()
                .myChallengeId(myChallenge.getMyChallengeId())
                .imagePath(myChallenge.getChallenge().getBadgeImage())
                .challengeName(myChallenge.getChallengeName())
                .content(myChallenge.getContent())
                .startAt(myChallenge.getStartAt())
                .endAt(myChallenge.getEndAt())
                .build();
    }

}
