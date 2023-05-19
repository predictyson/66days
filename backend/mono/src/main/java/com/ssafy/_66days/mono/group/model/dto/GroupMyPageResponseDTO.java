package com.ssafy._66days.mono.group.model.dto;

import com.ssafy._66days.mono.challenge.model.dto.ChallengeMyPageResponseDTO;
import com.ssafy._66days.mono.group.model.entity.Group;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupMyPageResponseDTO {
    private String image;
    private String name;
    private List<ChallengeMyPageResponseDTO> challenges;

    public static GroupMyPageResponseDTO of(Group group, List<ChallengeMyPageResponseDTO> challenges){
        return GroupMyPageResponseDTO
                .builder()
                .image(group.getImagePath())
                .name(group.getGroupName())
                .challenges(challenges)
                .build();
    }
}
