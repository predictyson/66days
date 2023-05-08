package com.ssafy._66days.article.model.entity.user;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auth {
    @Id
    @GeneratedValue
    @GenericGenerator(name="uuid", strategy="uuid4")
    @Column(name="auth_id", nullable= false, columnDefinition = "BINARY(16)")
    private UUID authId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name="provider")
    private String provider;

    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "withdrawal_at", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime withdrawalAt;

    @NotNull
    @Column(name="state", nullable = false, columnDefinition = "TINYINT(1)")
    private int state = 0;


}
