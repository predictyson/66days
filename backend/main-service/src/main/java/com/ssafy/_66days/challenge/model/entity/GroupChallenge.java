package com.ssafy._66days.challenge.model.entity;


import com.ssafy._66days.group.model.entity.Group;
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
    @Column(name = "group_challeng_id", nullable = false)
    private Long groupChallengeId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "group_id", nullable = false)
    private Group group;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @NotBlank
    @Column(name = "content", nullable = false)
    private String content;

    @NotBlank
    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @NotNull
    @Column(name = "start_at", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime endAt;

    @NotBlank
    @Column(name = "state", nullable = false)
    private String state;

    @NotNull
    @Column(name = "available_freezing_count", nullable = false)
    @ColumnDefault("0")
    private int availableFreezingCount;
    @NotNull
    @Column(name = "max_member_count", nullable = false)
    private int maxMemberCount;

}
