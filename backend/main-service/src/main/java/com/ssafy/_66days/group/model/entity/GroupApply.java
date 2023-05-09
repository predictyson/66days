package com.ssafy._66days.group.model.entity;

import com.ssafy._66days.user.model.entity.User;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "GROUP_APPLICATION")
@Data
public class GroupApply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_application_id")
    private Long groupApplyId;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "group_id")
    private Group group;
    @Column(name = "applied_at")
    @NotNull
    private LocalDateTime applyTime;
    @Column(name = "state")
    @NotNull
    private String state;
}
