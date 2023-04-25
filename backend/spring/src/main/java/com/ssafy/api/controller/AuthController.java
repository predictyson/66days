package com.ssafy.api.controller;

import com.ssafy.api.service.KakaoService;
import com.ssafy.commom.model.response.BaseResponseBody;
import com.ssafy.dto.KakaoUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

public class AuthController {
    @Autowired
    KakaoService kakaoService;
    public ResponseEntity<? extends BaseResponseBody> getKakaoCode(@RequestParam String code, HttpServletResponse res) {
        KakaoUserDto reqUser = kakaoService.getKakaoInfo(code);
    }
}
