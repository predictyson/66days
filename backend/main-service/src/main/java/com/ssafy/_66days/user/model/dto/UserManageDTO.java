package com.ssafy._66days.user.model.dto;

import com.ssafy._66days.group.model.entity.GroupMember;
import com.ssafy._66days.user.model.entity.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserManageDTO {
    private String image;
    private String nickname;
    private int badge; // TODO: 사용자 뱃지 수 가져오기
    private String authority;

    public static UserManageDTO of(User user, GroupMember groupMember){
        return UserManageDTO.builder()
                .image(user.getProfileImagePath())
                .nickname(user.getNickname())
                .authority(groupMember.getAuthority())
                .build();
    }

    public static UserManageDTO of(User user){
        return UserManageDTO.builder()
                .image(user.getProfileImagePath())
                .nickname(user.getNickname())
                .build();
    }
}
