package com.ssafy._66days.challenge.model.entity;

import com.ssafy._66days.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "my_challenge")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyChallenge {
    @Id
    @NotNull
    @Column(name = "my_challenge_id", nullable = false)
    private Long myChallengeId;

    @NotNull
    @ManyToOne
    @Column(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @Column(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @NotBlank
    @Column(name = "challenge_name", nullable = false)
    private String challengeName;

    @NotBlank
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "start_at", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime endAt;

    @NotBlank
    @Column(name = "state", nullable = false)
    private String state;
}
