package com.ssafy._66days.group.model.entity;

import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMemberId implements Serializable {
    private UUID user;
    private Long group;
}
