package com.ssafy._66days.challenge.model.dto.responseDTO;

import com.ssafy._66days.challenge.model.dto.MyChallengeHistoryDTO;
import com.ssafy._66days.challenge.model.entity.MyChallenge;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MyChallengeDetailDTO {
    private String challengeName;
    private String content;
    private String imagePath;
    private List<MyChallengeHistoryDTO> MyChallengeHistoryDTOS;

    public static MyChallengeDetailDTO of(MyChallenge myChallenge, List<MyChallengeHistoryDTO> MyChallengeHistoryDTOs) {
        return MyChallengeDetailDTO.builder()
                .challengeName(myChallenge.getChallengeName())
                .content(myChallenge.getContent())
                .imagePath(myChallenge.getChallenge().getBadge().getImagePath())
                .MyChallengeHistoryDTOS(MyChallengeHistoryDTOs)
                .build();
    }
}
