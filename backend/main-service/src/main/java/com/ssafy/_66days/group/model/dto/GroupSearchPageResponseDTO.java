package com.ssafy._66days.group.model.dto;

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
    private String animal;
}
