package com.ssafy.mock66days.member.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberMyPageResponseDTO {
    private String image;
    private String nickname;
    private String tier;
    private String email;
    private int currentExp;
    private int nextTierExp;
    private int point;
}
