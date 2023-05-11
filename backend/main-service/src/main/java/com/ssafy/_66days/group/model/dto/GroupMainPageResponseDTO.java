package com.ssafy._66days.group.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupMainPageResponseDTO {
    private String image;
    private String name;
    private List<String> badges;
    private String type;

}
