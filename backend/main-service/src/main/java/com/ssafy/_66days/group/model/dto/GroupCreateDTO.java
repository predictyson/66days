package com.ssafy._66days.group.model.dto;

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
}
