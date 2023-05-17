package com.ssafy._66days.user.model.entity;

import com.ssafy._66days.user.enums.Provider;
import com.ssafy._66days.user.enums.Role;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@DynamicInsert
@ToString
public class User {

	@Id
	@Column(name = "user_id", columnDefinition = "BINARY(16)", updatable = false)
	private UUID userId;

	@Enumerated(EnumType.STRING)
	@ColumnDefault("'ROLE_GUEST'")
	private Role role;

	@Column(name = "email", nullable = false, length = 320)
	private String email;

	@Enumerated(EnumType.STRING)
	private Provider provider;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, insertable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "withdrawal_at")
	private LocalDateTime withdrawalAt;

	public void withdraw() {
		this.email = "withdraw@withdraw.net";
		this.withdrawalAt = LocalDateTime.now();
	}

	public void signup() {
		this.role = Role.ROLE_USER;
	}
}
