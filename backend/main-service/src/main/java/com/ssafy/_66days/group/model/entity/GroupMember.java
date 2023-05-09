package com.ssafy._66days.group.model.entity;

import com.ssafy._66days.user.model.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@IdClass(GroupMemberId.class)
@Table(name="group_member")
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
}
