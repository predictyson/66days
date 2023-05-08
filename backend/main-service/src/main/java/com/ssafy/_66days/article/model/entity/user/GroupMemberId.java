package com.ssafy._66days.article.model.entity.user;

import com.ssafy._66days.article.model.entity.Group;

import java.io.Serializable;

public class GroupMemberId implements Serializable {
    private User userId;
    private Group groupId;
}
