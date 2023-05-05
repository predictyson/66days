package com.ssafy.mock66days.badge.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BadgeResponseDTO {
    private String image;
    private String challengeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String category;
    private boolean status;
}
