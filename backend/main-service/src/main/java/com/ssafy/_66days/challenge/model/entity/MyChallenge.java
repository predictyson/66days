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
    @Column(name = "my_challenge_id")
    private Long myChallengeId;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
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
}
