package com.ssafy._66days.mainservice.challenge.model.dto.responseDTO;

import com.ssafy._66days.mainservice.challenge.model.dto.MyChallengeHistoryDTO;
import com.ssafy._66days.mainservice.challenge.model.entity.MyChallenge;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MyChallengeDetailResponseDTO {
    private String challengeName;
    private String content;
    private String imagePath;
    private List<MyChallengeHistoryDTO> MyChallengeHistoryDTOS;

    public static MyChallengeDetailResponseDTO of(MyChallenge myChallenge, List<MyChallengeHistoryDTO> myChallengeHistoryDTOs) {
        return MyChallengeDetailResponseDTO.builder()
                .challengeName(myChallenge.getChallengeName())
                .content(myChallenge.getContent())
                .imagePath(myChallenge.getChallenge().getBadge().getImagePath())
                .MyChallengeHistoryDTOS(myChallengeHistoryDTOs)
                .build();
    }
}
