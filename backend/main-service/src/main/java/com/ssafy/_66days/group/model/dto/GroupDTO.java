package com.ssafy._66days.group.model.dto;

import com.ssafy._66days.group.model.entity.Group;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupDTO {
    private Long groupId;
    private String groupName;
    private int maxMemberCount;
    private String imagePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

    public GroupDTO(Group group) {
        this.groupId = group.getGroupId();
        this.groupName = group.getGroupName();
        this.maxMemberCount = group.getMaxMemberCount();
        this.imagePath = group.getImagePath();
        this.createdAt = group.getCreatedAt();
        this.updatedAt = group.getUpdatedAt();
        this.isDeleted = group.isDeleted();

    }


}
