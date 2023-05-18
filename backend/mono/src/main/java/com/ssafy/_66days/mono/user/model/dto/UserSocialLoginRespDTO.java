package com.ssafy._66days.mono.user.model.dto;

import com.ssafy._66days.mono.user.model.entity.User;
import com.ssafy._66days.mono.user.vo.ProviderType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSocialLoginRespDTO {
	private String email;
	private String nickName;
	private ProviderType social;
	
    public static UserSocialLoginRespDTO of(User user) {
    	return UserSocialLoginRespDTO.builder()
    			.email(user.getEmail())
    			.nickName(user.getNickname())
    			.social(user.getSocial())
    			.build();
    }
}
