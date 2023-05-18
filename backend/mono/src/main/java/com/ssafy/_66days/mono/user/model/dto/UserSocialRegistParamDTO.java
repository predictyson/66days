package com.ssafy._66days.mono.user.model.dto;

import com.ssafy._66days.mono.user.model.entity.User;
import com.ssafy._66days.mono.user.vo.ProviderType;
import com.ssafy._66days.mono.user.vo.RoleType;
import lombok.*;

import java.util.Random;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSocialRegistParamDTO {
	private String email;
	private String nickname;
	private String social;
	
	public static User of(UserSocialRegistParamDTO userDTO) {
		Random random = new Random();
		Long randomNumber = Math.abs(random.nextLong() % 20) + 1;
		return User.builder()
				.userId(UUID.randomUUID())
				.email(userDTO.getEmail())
				.nickname(userDTO.getNickname())
				.animalId(randomNumber)
				.exp(0L)
				.point(0L)
				.profileImagePath("/users/basic_profile.png")
				.tierId(1L)
				.social(ProviderType.KAKAO)
				.roleType(RoleType.USER)
				.build();
	}
}
