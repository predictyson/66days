package com.ssafy._66days.group.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GroupMemberKey implements Serializable {
    private Long userId;
    private Long groupId;
}
