package com.ssafy.mock66days.group.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class GroupMainPageResponseDTO {
    private String image;
    private String name;
    private List<String> badges;
    private String type;

    @Builder
    public GroupMainPageResponseDTO of(String image, String name, List<String> badges, String type) {
        return builder()
                .image(image)
                .name(name)
                .badges(badges)
                .type(type)
                .build();
    }
}
