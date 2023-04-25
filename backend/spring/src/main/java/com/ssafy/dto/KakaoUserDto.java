package com.ssafy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class KakaoUserDto {
    String accessToken;
    String refreshToken;
    Long refreshTokenExpiresIn;
    Long expiresIn;
}
