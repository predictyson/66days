package ssafy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.auth.ApiResponse;
import com.kakao.auth.AuthApiClient;
import com.kakao.auth.AuthApiException;
import com.kakao.auth.AuthCodeReceiveException;
import com.kakao.auth.AuthToken;
import com.kakao.auth.AuthType;
import com.kakao.auth.ResponseType;
import com.kakao.auth.authorization.AuthorizationResult;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.auth.authorization.accesstoken.AccessTokenStore;
import com.kakao.auth.authorization.authcode.AuthorizationCode;
import com.kakao.auth.authorization.authcode.AuthorizationCodeRequest;
import com.kakao.auth.authorization.authcode.AuthorizationCodeService;
import com.kakao.auth.authorization.authcode.AuthorizationCodeVerifier;
import com.kakao.auth.authorization.authcode.AuthorizationCodeVerifierFactory;
import com.kakao.auth.authorization.authcode.AuthorizationUrl;
import com.kakao.auth.authorization.authcode.AuthorizationUrlRequest;
import com.kakao.auth.exception.AuthorizationFailedException;
import com.kakao.auth.exception.ClientErrorException;
import com.kakao.auth.exception.ServerErrorException;
import com.kakao.auth.helper.UrlHelper;
import com.kakao.auth.response.OAuthToken;
import com.kakao.auth.response.ResponseAuth;
import ssafy.api.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 카카오톡 로그인 요청
    @PostMapping("/login/kakao")
    @ResponseBody
    public String kakaoLogin(@RequestParam("code") String code) {
        try {
            // 카카오톡 API 클라이언트 설정
            AuthApiClient apiClient = new AuthApiClient("KAKAO_CLIENT_ID");
            // Authorization Code 요청 객체 생성
            AuthorizationCodeRequest request = AuthorizationCodeRequest.newBuilder(code)
                    .build();
            // Authorization Code 검증기 생성
            AuthorizationCodeVerifier verifier = AuthorizationCodeVerifierFactory.createVerifier(request);
            // Authorization Code 검증
            AuthorizationResult result = verifier.verify(apiClient);

            // Access Token 발급 요청 객체 생성
            AuthorizationCode authorizationCode = result.getAuthorizationCode();
            AuthorizationUrlRequest tokenRequest = AuthorizationUrlRequest.newBuilder(ResponseType.CODE)
                    .setClientId("KAKAO_CLIENT_ID")
                    .setRedirectUri(UrlHelper.buildUri("/login/kakao"))
                    .setAuthorizationCode(authorizationCode)
                    .build();
            // Access Token 발급
            ApiResponse<OAuthToken, AuthApiException> tokenResponse = apiClient.issueAccessToken(tokenRequest);
            OAuthToken oAuthToken = tokenResponse.getResult();

            // Access Token 저장
            AccessTokenStore.getInstance().setAccessToken(new AccessToken(oAuthToken.getAccessToken(),
                    oAuthToken.getAccessTokenExpiresAt(),
                    oAuthToken.getScopes(),
                    AuthType.KAKAO_TALK));

            // 사용자 정보 요청
            ResponseAuth<KakaoProfile> response = apiClient.me();
            KakaoProfile profile = response.getResult();

            // 회원가입 또는 로그인 처리
            // 카카오톡 사용자 정보를 기반으로 회원가입 또는 로그인 처리를 수행하고, 세션 등의 처리를 수
            // 카카오톡 사용자 정보를 기반으로 회원가입 또는 로그인 처리
            String userId = profile.getId().toString(); // 사용자 고유 아이디
            String nickname = profile.getNickname(); // 사용자 닉네임
            String email = profile.getEmail(); // 사용자 이메일
            // 기타 필요한 정보들을 추출하여 회원가입 또는 로그인 처리 수행

            // 회원가입 또는 로그인 성공 시에는 성공 메시지를 반환
            return "회원가입 또는 로그인이 성공적으로 처리되었습니다.";
        } catch (AuthorizationCodeReceiveException | AuthorizationFailedException | ClientErrorException
                 | ServerErrorException e) {
            // 처리 중 에러 발생 시 에러 메시지를 반환
            return "회원가입 또는 로그인 중 에러가 발생하였습니다. : " + e.getMessage();
        }
    }
}

