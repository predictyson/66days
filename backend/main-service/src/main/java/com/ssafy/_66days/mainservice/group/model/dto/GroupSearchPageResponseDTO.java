package com.ssafy._66days.mainservice.group.model.dto;

import com.ssafy._66days.mainservice.group.model.entity.Group;
import com.ssafy._66days.mainservice.user.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupSearchPageResponseDTO {
    private String ownerImage;
    private String ownerName;
    private String image;
    private String name;
    private List<String> categories;
    private String description;
    private Long memberCounts;
    private int maxMemberCounts;

    public static GroupSearchPageResponseDTO of(Group group, User user) {
        return GroupSearchPageResponseDTO.builder()
                .ownerImage(user.getProfileImagePath())
                .ownerName(user.getNickname())
                .image(group.getImagePath())
                .name(group.getGroupName())
                .description(group.getDescription())
                .maxMemberCounts(group.getMaxMemberCount())
                .build();
    }
}
