package com.ssafy._66days.user.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import com.ssafy._66days.user.enums.Provider;
import com.ssafy._66days.user.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@DynamicInsert
public class User {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)", updatable = false, name = "user_id")
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
}
