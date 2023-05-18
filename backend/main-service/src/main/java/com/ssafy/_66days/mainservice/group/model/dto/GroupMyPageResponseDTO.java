package com.ssafy._66days.mainservice.group.model.dto;

import com.ssafy._66days.mainservice.challenge.model.entity.Challenge;
import com.ssafy._66days.mainservice.group.model.entity.Group;
import com.ssafy._66days.mainservice.group.model.entity.GroupMember;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupMyPageResponseDTO {
    private String image;
    private String name;
    private List<Challenge> challenges;

    public static GroupMyPageResponseDTO of(Group group, List<Challenge> challenges){
        return GroupMyPageResponseDTO
                .builder()
                .image(group.getImagePath())
                .name(group.getGroupName())
                .challenges(challenges)
                .build();
    }
}
