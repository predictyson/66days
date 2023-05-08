package com.ssafy.mock66days.challenge.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ChallengeMainPageResponseDTO {
    private String image;
    private String name;
    private List<String> participants;
    private LocalDate startDate;
    private boolean status;

}
