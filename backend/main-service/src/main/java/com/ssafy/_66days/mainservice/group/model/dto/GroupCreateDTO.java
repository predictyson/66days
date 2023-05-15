package com.ssafy._66days.mainservice.group.model.dto;

import com.ssafy._66days.mainservice.group.model.entity.Group;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GroupCreateDTO {
    private Long groupId;
    private String name;
    private String description;
    private int maxMember;
    private String image;
    private LocalDateTime createTime;
    private LocalDateTime updatedTime;
    private boolean isDeleted;

    public static Group toEntity(GroupCreateDTO groupCreateDTO) {
        return Group.builder()
                .groupName(groupCreateDTO.getName())
                .description(groupCreateDTO.getDescription())
                .maxMemberCount(groupCreateDTO.getMaxMember())
                .imagePath(groupCreateDTO.getImage())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
