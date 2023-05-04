package com.ssafy.mock66days.member.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
    private String image;
    private String name;
    private String animal;
    private String tier;
    private int exp;
    private int badge;

}
