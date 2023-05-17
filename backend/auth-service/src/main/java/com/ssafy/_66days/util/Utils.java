package com.ssafy._66days.util;

public class Utils {
	// Character
	public static final String BLANK = " ";
	public static final String QUESTION_MARK = "?";
	public static final String EQUALS_SIGN = "=";
	public static final String AMPERSAND = "&";

	// JWT AND Auth
	public static final String ROLE = "role";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
	public static final String BEARER_TOKEN_PREFIX = "Bearer ";
	public static final String ACCESS_TOKEN = "AccessToken";
	public static final String REFRESH_TOKEN = "RefreshToken";
	public static final String AUTHORIZATION = "Authorization";
	public static final String RT_COLON = "RT:";
	public static final String TOKEN = "token";
	public static final String KAKAO_ACCOUNT = "kakao_account";
	public static final String EMAIL = "email";

	public static String getQueryParameter(String key, String value) {
		return new StringBuilder()
			.append(key)
			.append(Utils.EQUALS_SIGN)
			.append(value)
			.toString();
	}
}
