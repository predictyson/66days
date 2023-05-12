package com.ssafy._66days.badge.model.service;

import com.ssafy._66days.badge.model.dto.ResponseDTO.BadgeDetailResponseDTO;
import com.ssafy._66days.badge.model.dto.ResponseDTO.BadgeListResponseDTO;
import com.ssafy._66days.badge.model.entity.Badge;
import com.ssafy._66days.badge.model.repository.BadgeRepository;
import com.ssafy._66days.global.util.CheckUserUtil;
import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.group.model.repository.GroupRepository;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.repository.UserRepository;
import com.ssafy._66days.user.model.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final String userIdStr = "a817d372-ee0d-11ed-a26b-0242ac110003";
    private final UUID userId = UUID.fromString(userIdStr);

    public BadgeService(
            BadgeRepository badgeRepository,
            GroupRepository groupRepository,
            GroupMemberRepository groupMemberRepository,
            UserRepository userRepository,
            UserService userService
            ) {
        this.badgeRepository = badgeRepository;
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<BadgeListResponseDTO> getGroupBadgeList(
            Long groupId
    ) {
        CheckUserUtil checkUser = new CheckUserUtil(groupMemberRepository, userService);
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));

        boolean isUserInGroup = checkUser.isUserInGroup(group, user);
        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에서 탈퇴한 유저입니다");
        }

        return null;
    }

    public List<BadgeDetailResponseDTO> getGroupBadge(
            Long groupId,
            Long badgeId
    ) {
        return null;
    }

    public List<BadgeListResponseDTO> getPrivateBadgeList() {
        return null;
    }
    public List<BadgeDetailResponseDTO> getPrivateBadge(
            Long badgeId
    ) {
        return null;
    }
}
