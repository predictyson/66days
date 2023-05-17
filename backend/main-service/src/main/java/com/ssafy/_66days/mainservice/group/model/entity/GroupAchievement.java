package com.ssafy._66days.mainservice.group.model.entity;

import com.ssafy._66days.mainservice.challenge.model.entity.Challenge;
import com.ssafy._66days.mainservice.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@IdClass(GroupAchievementId.class)
@Table(name = "group_achievement")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupAchievement {
    @Id
    @NotNull
    @OneToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Id
    @NotNull
    @OneToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @NotNull
    @Column(name = "achievement_count")
    private int achievementCount;
}
