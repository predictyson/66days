package com.ssafy.mock66days.challenge.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChallengeMainPageResponseDTO {
    private String image;
    private String name;
    private List<String> participants;
    private LocalDateTime startDate;
    private String status;

    @Builder
    public ChallengeMainPageResponseDTO of(String image, String name, List<String> participants, LocalDateTime startDate, String status){
        return builder()
                .image(image)
                .name(name)
                .participants(participants)
                .startDate(startDate)
                .status(status)
                .build();
    }
}
