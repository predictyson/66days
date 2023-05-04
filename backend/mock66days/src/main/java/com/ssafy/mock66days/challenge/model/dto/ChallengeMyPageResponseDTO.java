package com.ssafy.mock66days.challenge.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ChallengeMyPageResponseDTO {
    private String category;
    private String name;
    private LocalDateTime startDate;
}
