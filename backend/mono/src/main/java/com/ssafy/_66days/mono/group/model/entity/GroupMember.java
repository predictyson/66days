package com.ssafy._66days.mono.group.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import com.ssafy._66days.mono.user.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@IdClass(GroupMemberId.class)
@Table(name = "group_member")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupMember {

	@Id
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Id
	@NotNull
	@ManyToOne
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;

	@NotNull
	@Column(name = "authority", nullable = false)
	private String authority;

	@NotNull
	@Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = true, updatable = true, columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedAt;

	@NotNull
	@Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1)")
	@ColumnDefault("0")
	private boolean isDeleted;

	public void updateAuthority(String authority) {
		this.authority = authority;
		this.updatedAt = LocalDateTime.now();
	}

	public void updateIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
