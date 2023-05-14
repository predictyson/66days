package com.ssafy._66days.challenge.model.entity;

import com.ssafy._66days.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@IdClass(GroupChallengeMemberId.class)
@Entity
@Table(name = "group_challenge_member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupChallengeMember {
    @Id
    @NotNull
    @ManyToOne
    private User user;

    @Id
    @NotNull
    @ManyToOne
    private GroupChallenge groupChallenge;
}
