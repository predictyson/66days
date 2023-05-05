package com.ssafy.mock66days.challenge.model.dto;

import com.ssafy.mock66days.member.model.dto.MemberDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ChallengeGroupPageResponseDTO {
    private String image;
    private String name;
    private List<MemberDTO> participants;
    private int maxParticipant;
    private LocalDateTime startDate;
}
