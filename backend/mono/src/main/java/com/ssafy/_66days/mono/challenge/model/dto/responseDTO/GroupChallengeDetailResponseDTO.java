package com.ssafy._66days.mono.challenge.model.dto.responseDTO;

import com.ssafy._66days.mono.challenge.model.dto.GroupChallengeMemberDTO;
import com.ssafy._66days.mono.challenge.model.entity.GroupChallenge;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupChallengeDetailResponseDTO {
    private Long groupChallengeId;
    private String groupChallengeName;
    private String content;
    private String imagePath;
    private List<GroupChallengeMemberDTO> memberDetail;

    public static GroupChallengeDetailResponseDTO of(GroupChallenge groupChallenge, List<GroupChallengeMemberDTO> groupChallengeMemberDTOList) {
        return GroupChallengeDetailResponseDTO.builder()
                .groupChallengeId(groupChallenge.getGroupChallengeId())
                .groupChallengeName(groupChallenge.getChallengeName())
                .content(groupChallenge.getContent())
                .imagePath(groupChallenge.getChallenge().getBadge().getImagePath())
                .memberDetail(groupChallengeMemberDTOList)
                .build();

    }
}
