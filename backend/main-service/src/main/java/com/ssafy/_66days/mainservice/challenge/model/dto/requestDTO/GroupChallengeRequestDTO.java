package com.ssafy._66days.mainservice.challenge.model.dto.requestDTO;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupChallengeRequestDTO {
    private Long challengeId;
    private String challengeName;
    private int maxMemberCount;
    private String content;
    private Date startAt;

}
