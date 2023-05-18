package com.ssafy._66days.mono.challenge.model.entity;


import com.ssafy._66days.mono.group.model.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_challenge")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_challenge_id")
    private Long groupChallengeId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @NotBlank
    @Column(name = "challenge_name")
    private String challengeName;

    @NotBlank
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "start_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime startAt;

    @Column(name = "end_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime endAt;

    @NotBlank
    @Column(name = "state")
    private String state;

    @NotNull
    @Column(name = "available_freezing_count")
    @ColumnDefault("0")
    private int availableFreezingCount;
    @NotNull
    @Column(name = "max_member_count")
    private int maxMemberCount;

}
