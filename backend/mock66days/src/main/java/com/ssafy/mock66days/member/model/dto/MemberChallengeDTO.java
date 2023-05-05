package com.ssafy.mock66days.member.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberChallengeDTO {
    private String image;
    private String nickname;
    private boolean status;
}
