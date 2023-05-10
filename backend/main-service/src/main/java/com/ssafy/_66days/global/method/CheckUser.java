package com.ssafy._66days.global.method;

import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.group.model.entity.GroupMember;
import com.ssafy._66days.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
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
        GroupMember groupMember = groupMemberRepository.findByGroupAndUser(group, user)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속하지 않은 유저입니다"));
        if (!groupMember.isDeleted()) {
            return true;
        }
        return false;
    }
}
