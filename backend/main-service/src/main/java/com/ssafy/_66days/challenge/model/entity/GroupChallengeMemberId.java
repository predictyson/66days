package com.ssafy._66days.challenge.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupChallengeMemberId implements Serializable {
    private UUID user;
    private Long groupChallenge;
}
