package com.ssafy._66days.mainservice.badge.model.service;

import com.ssafy._66days.mainservice.badge.model.dto.ResponseDTO.BadgeDetailResponseDTO;
import com.ssafy._66days.mainservice.badge.model.dto.ResponseDTO.BadgeListResponseDTO;
import com.ssafy._66days.mainservice.badge.model.dto.ResponseDTO.BadgeMyPageDTO;
import com.ssafy._66days.mainservice.badge.model.repository.BadgeRepository;
import com.ssafy._66days.mainservice.challenge.model.entity.MyChallenge;
import com.ssafy._66days.mainservice.challenge.model.reposiotry.MyChallengeRepository;
import com.ssafy._66days.mainservice.global.util.CheckUserUtil;
import com.ssafy._66days.mainservice.group.model.entity.Group;
import com.ssafy._66days.mainservice.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.mainservice.group.model.repository.GroupRepository;
import com.ssafy._66days.mainservice.user.model.entity.User;
import com.ssafy._66days.mainservice.user.model.repository.UserRepository;
import com.ssafy._66days.mainservice.user.model.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final MyChallengeRepository myChallengeRepository;
//    private final String userIdStr = "a817d372-ee0d-11ed-a26b-0242ac110003";
//    private final UUID userId = UUID.fromString(userIdStr);

    private final String SUCCESS = "SUCCESSFUL";
    public List<BadgeListResponseDTO> getGroupBadgeList(
            UUID userId,
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

    public List<BadgeMyPageDTO> getMyPageBadgeList(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        List<MyChallenge> myChallengeList = myChallengeRepository.findDistinctChallengeIdByUserAndState(user, SUCCESS);
        List<BadgeMyPageDTO> badgeMyPageDTOList = new ArrayList<>();
        for (MyChallenge challenge:myChallengeList) {
            badgeMyPageDTOList.add(BadgeMyPageDTO.of(challenge.getChallenge()));
        }
        return badgeMyPageDTOList;
    }
}
