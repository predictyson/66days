package com.ssafy._66days.mainservice.group.model.entity;

import com.ssafy._66days.mainservice.user.model.entity.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    public void updateGroupApply(String state) {
        this.state = state;
    }

    public GroupApply(User user, Group group, String state) {
        this.user = user;
        this.group = group;
        this.state = state;
    }
}
