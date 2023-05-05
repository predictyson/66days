package com.ssafy.mock66days.challenge.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ChallengeRequestDTO {
    private String name;
    private int number;
    private String description;
    private LocalDate startDate;
}

