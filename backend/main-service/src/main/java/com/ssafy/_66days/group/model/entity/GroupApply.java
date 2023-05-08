package com.ssafy._66days.group.model.entity;

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
    @Column(name = "user_id")
    @NotNull
    private UUID userId;
    @Column(name = "group_id")
    @NotNull
    private Long groupId;
    @Column(name = "application_datetime")
    @NotNull
    private LocalDateTime applyTime;
    @Column(name = "state")
    @NotNull
    private boolean state;
}
