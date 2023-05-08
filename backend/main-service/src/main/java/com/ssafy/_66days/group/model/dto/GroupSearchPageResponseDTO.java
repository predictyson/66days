package com.ssafy._66days.group.model.dto;

import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.user.model.dto.UserGroupSearchPageResponseDTO;
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
    private int memberCounts;
    private int maxMemberCounts;

    public static GroupSearchPageResponseDTO of(Group group) {
        UserGroupSearchPageResponseDTO userDTO =
        return GroupSearchPageResponseDTO.builder()
                .image(group.getImage())
                .name(group.getName())
                .description(group.getDescription())
                .maxMemberCounts(group.getMaxMember())
                .build();
    }
}
