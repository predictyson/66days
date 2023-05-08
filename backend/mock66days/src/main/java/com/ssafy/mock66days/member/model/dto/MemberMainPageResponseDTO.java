package com.ssafy.mock66days.member.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberMainPageResponseDTO {

    private String tier;
    private String email;
    private int exp;
    private int point;
    private String image;
    private String animal;
}
