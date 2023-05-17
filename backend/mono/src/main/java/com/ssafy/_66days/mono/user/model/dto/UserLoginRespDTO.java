package com.ssafy._66days.mono.user.model.dto;

import com.ssafy._66days.mono.user.model.entity.User;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginRespDTO {
	private String email;
	private UUID userId;
	private String nickName;
	private String address;
	
    public static UserLoginRespDTO of(User user) {
    	return UserLoginRespDTO.builder()
    			.email(user.getEmail())
    			.userId(user.getUserId())
    			.nickName(user.getNickname())
    			.build();
    }
}
