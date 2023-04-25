package com.ssafy.api.service;

import com.ssafy.dto.KakaoUserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class KakaoService {
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    @Value("${spring.security.oauth2.client.registration.kakao.clinet-id}")
    private String KAKAO_KEY;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-url}")
    private String KAKAO_REDIRECT_URL;

    public KakaoUserDto getKakaoInfo(String code) {
        String accessToken = "";
        String refreshToken = "";
        Long refreshTokenExpiresIn = 0L;
        Long expiresIn = 0L;
        KakaoUserDto kakaoUser = KakaoUserDto.builder().build();
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("&client_id=" + KAKAO_KEY);
            sb.append("&redirect_url=" + KAKAO_REDIRECT_URL);
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();




        } catch (IOException e) {
            e.printStackTrace();
        }
        return kakaoUser;
    }
}
