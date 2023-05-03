package com.ssafy.mock66days.member.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberMainPageResponseDTO {

    private String tier;
    private String email;
    private int exp;
    private int point;
    private String image;
    private String animal;

    @Builder
    public MemberMainPageResponseDTO of(String tier, String email, int exp, int point, String image, String animal) {
        return builder()
                .tier(tier)
                .email(email)
                .exp(exp)
                .point(point)
                .image(image)
                .animal(animal)
                .build();
    }
}
