package com.ssafy._66days.mono.global.util;

import com.ssafy._66days.mono.group.model.entity.Group;
import com.ssafy._66days.mono.group.model.entity.GroupMember;
import com.ssafy._66days.mono.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.mono.user.model.entity.User;
import com.ssafy._66days.mono.user.model.service.UserService;

public class CheckUserUtil {
	private final GroupMemberRepository groupMemberRepository;
	private final UserService userService;

	public CheckUserUtil(GroupMemberRepository groupMemberRepository, UserService userService) {
		this.groupMemberRepository = groupMemberRepository;
		this.userService = userService;
	}
	// auth 서버에서 user 검증을 하기 때문에 필요없어짐
	//    public UUID isExistUser(
	//            UserDetails currentUser
	//    ) {
	//
	//        String username = currentUser.getUsername();
	//        UUID userUuid = userService.getUserUuidByNickname(username);
	//        return userUuid;
	//
	//    }

	// 해당 유저가 해당 그룹에 속하고 탈퇴한 회원인지 아닌지 판별해주는 함수
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
