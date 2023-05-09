package com.ssafy._66days.global.method;

import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.group.model.entity.GroupMember;
import com.ssafy._66days.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CheckUser {
    private final GroupMemberRepository groupMemberRepository;
    private final UserService userService;

    public CheckUser(GroupMemberRepository groupMemberRepository, UserService userService) {
        this.groupMemberRepository = groupMemberRepository;
        this.userService = userService;
    }
    public UUID isExistUser(
            UserDetails currentUser
    ) {

        String username = currentUser.getUsername();
        UUID userUuid = userService.getUserUuidByNickname(username);
        return userUuid;

    }

    public boolean isUserInGroup(
            Group group,
            User user
    ) {
        Optional<GroupMember> groupMember = groupMemberRepository.findByGroupAndUser(group, user);
        if (groupMember.isPresent() && !groupMember.get().isDeleted()) {
            return true;
        }
        return false;
    }
}
