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

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import com.ssafy._66days.user.enums.Role;

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
	@Column(columnDefinition = "BINARY(16)", updatable = false)
	private UUID userId;

	@Enumerated(EnumType.STRING)
	@ColumnDefault("'ROLE_GUEST'")
	private Role role;

	@Column(name = "email", nullable = false, length = 320)
	private String email;

	@Column(name = "provider", nullable = false, length = 15)
	private String provider;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, insertable = false, updatable = false)
	private LocalDateTime createdAt;

	private LocalDateTime withdrawalAt;

	public void withdraw() {
		this.withdrawalAt = LocalDateTime.now();
	}
}
