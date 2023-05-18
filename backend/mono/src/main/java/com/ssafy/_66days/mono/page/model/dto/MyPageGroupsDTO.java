package com.ssafy._66days.mono.page.model.dto;

import com.ssafy._66days.mono.group.model.entity.Group;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MyPageGroupsDTO {
    private Long groupId;
    private String name;
    private List<String> challenges;

    public static MyPageGroupsDTO of(Group group, List<String> challenges) {
        return MyPageGroupsDTO.builder()
                .groupId(group.getGroupId())
                .name(group.getGroupName())
                .challenges(challenges)
                .build();
    }
}
