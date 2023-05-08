package com.ssafy._66days.article.model.dto;

import com.ssafy._66days.article.model.entity.user.Auth;
import com.ssafy._66days.article.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDTO {
    private UUID authId;
    private User userId;
    private String email;
    private String provider;

    private LocalDateTime createdAt;
    private LocalDateTime withdrawalAt;
    private int state;

    public AuthDTO(Auth auth) {
        this.authId = auth.getAuthId();
        this.userId = auth.getUserId();
        this.email = auth.getEmail();
        this.provider = auth.getProvider();
        this.createdAt = auth.getCreatedAt();
        this.withdrawalAt = auth.getWithdrawalAt();
        this.state = auth.getState();
    }
}

