package com.ssafy._66days.mainservice.challenge.model.entity;

import com.ssafy._66days.mainservice.badge.model.entity.Badge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "challenge")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Challenge {    // 챌린지 메타 데이터
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long challengeId;

    @NotNull
    @OneToOne   // 챌린지와 뱃지는 1대1 매핑
    @JoinColumn(name = "badge_id")
    private Badge badge;

    @NotBlank
    @Column(name = "topic")
    private String topic;

    @NotBlank
    @Column(name = "badge_image")
    private String badgeImage;
}
