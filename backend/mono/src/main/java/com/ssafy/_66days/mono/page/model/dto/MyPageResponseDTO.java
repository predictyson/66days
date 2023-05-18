package com.ssafy._66days.mono.page.model.dto;

import com.ssafy._66days.mono.user.model.dto.UserDTO;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MyPageResponseDTO {
    private UserDTO userDetail;

    private List<MyPageBadgeDTO> badges;
    private List<MyPageGroupsDTO> groups;
    private List<MyPageChallengeDTO> Challenges;

    public static MyPageResponseDTO of(UserDTO userDetail, List<MyPageBadgeDTO> badges, List<MyPageGroupsDTO> groups, List<MyPageChallengeDTO> Challenges) {
        return MyPageResponseDTO.builder()
                .userDetail(userDetail)
                .badges(badges)
                .groups(groups)
                .Challenges(Challenges)
                .build();
    }

}
