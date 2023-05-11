package com.ssafy._66days.challenge.model.entity;

import com.ssafy._66days.group.model.entity.GroupMemberId;
import com.ssafy._66days.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

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
    @Column(name = "user_id", nullable = false)
    private User user;

    @Id
    @NotNull
    @ManyToOne
    @Column(name = "group_challenge_id", nullable = false)
    private GroupChallenge groupChallenge;
}
