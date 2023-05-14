package com.ssafy._66days.challenge.model.dto;

import com.ssafy._66days.challenge.model.entity.GroupChallengeMember;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupChallengeMemberDTO {
    private String imagePath;
    private String nickName;
    // ? 오늘 스트릭 찍었는지 안찍엇는지 정보 mongoDB 이슈

    public static GroupChallengeMemberDTO of(GroupChallengeMember groupChallengeMember) {
        return GroupChallengeMemberDTO.builder()
                .imagePath(groupChallengeMember.getUser().getProfileImagePath())
                .nickName(groupChallengeMember.getUser().getNickname())
                .build();
    }
}
