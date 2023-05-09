package com.ssafy._66days.group.model.entity;

import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.user.model.entity.User;

import java.io.Serializable;

public class GroupMemberId implements Serializable {
    private User user;
    private Group group;
}
