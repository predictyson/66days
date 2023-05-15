package com.ssafy._66days.mainservice.global.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy._66days.mainservice.user.model.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.UUID;

public class AuthenticateUtil {
    public UUID getUserId(String accessToken) throws IOException {
        //RestTemplate는 HTTP 요청을 만들고 보내고 결과를 받는데 사용
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://{auth_server_address}/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        User user = new ObjectMapper().readValue(response.getBody(), User.class);
        return user.getUserId();
    }
}

