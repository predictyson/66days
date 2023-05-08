package com.ssafy.mock66days.badge.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadgeGroupPageResponseDTO {
    private String image;
    private String category;
    private int count;
}
