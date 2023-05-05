package com.ssafy.mock66days.streak.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StreakGroupResponseDTO {
    private LocalDate date;
    private String status;
    private int count;
}
