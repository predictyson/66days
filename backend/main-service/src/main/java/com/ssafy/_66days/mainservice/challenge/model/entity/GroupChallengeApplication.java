package com.ssafy._66days.mainservice.challenge.model.entity;

import com.ssafy._66days.mainservice.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_challenge_application")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupChallengeApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_challenge_appllication_id")
    private Long groupChallengeApplicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_challenge_id")
    private GroupChallenge groupChallenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    @Column(name = "state")
    private String state;

    @NotNull
    @Column(name = "applied_at")
    private LocalDateTime appliedAt;

    public void updateState(String state) {
        this.state = state;
    }
}
