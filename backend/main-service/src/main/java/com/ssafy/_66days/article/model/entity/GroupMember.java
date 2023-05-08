package com.ssafy._66days.article.model.entity;

import com.ssafy._66days.article.model.entity.user.GroupMemberId;
import com.ssafy._66days.article.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@IdClass(GroupMemberId.class)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupMember {

    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group groupId;

    @NotNull
    @Column(name = "authority", nullable = false)
    private Long authority;

    @NotNull
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true, updatable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @NotNull
    @Column(name = "withdrawal_at", nullable = false, columnDefinition = "TINYINT")
    private int withdrawalAt = 0;

}
