package com.ssafy._66days.mainservice.group.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupAchievementId implements Serializable {
    private Long group;
    private Long challenge;
}
