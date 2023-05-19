package com.ssafy._66days.security;

import java.nio.ByteBuffer;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import com.ssafy._66days.exception.ForbiddenException;
import com.ssafy._66days.exception.RefreshTokenNotFoundException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ssafy._66days.user.enums.Role;
import com.ssafy._66days.user.model.dto.CustomOAuth2User;
import com.ssafy._66days.user.model.dto.JwtDTO;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.repository.UserRepository;
import com.ssafy._66days.util.RedisUtil;
import com.ssafy._66days.util.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider implements InitializingBean {

	private final RedisUtil redisUtil;
	private final UserRepository userRepository;
	private final long accessTokenValidTime;
	private final long refreshTokenValidTime;
	private final String secretKey;
	private Key key;

	public JwtProvider(@Value("${jwt.access-token-valid-time}") long accessTokenValidTime,
			@Value("${jwt.refresh-token-valid-time}") long refreshTokenValidTime,
			@Value("${jwt.secret-key}") String secretKey,
			UserRepository userRepository,
			RedisUtil redisUtil) {
		this.redisUtil = redisUtil;
		this.userRepository = userRepository;
		this.secretKey = secretKey;
		this.refreshTokenValidTime = refreshTokenValidTime;
		this.accessTokenValidTime = accessTokenValidTime;
	}

	@Override
	public void afterPropertiesSet() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	/**
	 * 유저 인증 정보로 토큰 생성 (카카오 로그인 시)
	 */
	public String createTokensFromAuthentication(Authentication authentication, String tokenType) {
		CustomOAuth2User customOAuth2User = (CustomOAuth2User)authentication.getPrincipal();
		Date currentTime = new Date();
		Date expirationTime = calcTokenExpirationTime(currentTime, tokenType);
		String token = Jwts.builder()
				.setSubject(customOAuth2User.getUserId().toString())
				.claim(Utils.ROLE, customOAuth2User.getRole())
				.setIssuedAt(currentTime)
				.setExpiration(expirationTime)
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();

		if (tokenType.equals(Utils.REFRESH_TOKEN)) {
			String key = Utils.RT_COLON + Encoders.BASE64.encode(uuidToByteArray(customOAuth2User.getUserId()));
			redisUtil.setDataExpire(key, token, refreshTokenValidTime);
			log.info("createTokensFromAuthentication | key: {}", key);
		}
		return token;
	}

	/**
	 * 유저 정보로 토큰 생성
	 */
	public String createTokensFromUserPrincipal(UserPrincipal userPrincipal, String tokenType) {
		Date currentTime = new Date();
		Date expirationTime = calcTokenExpirationTime(currentTime, tokenType);

		String token = Jwts.builder()
				.setSubject(userPrincipal.getUserId().toString())
				.claim(Utils.ROLE, userPrincipal.getRole())
				.setIssuedAt(currentTime)
				.setExpiration(expirationTime)
				.signWith(
						key,
						SignatureAlgorithm.HS512
				)
				.compact();

		if (tokenType.equals(Utils.REFRESH_TOKEN)) {
			String key = Utils.RT_COLON + Encoders.BASE64.encode(uuidToByteArray(userPrincipal.getUserId()));
			redisUtil.setDataExpire(key, token, refreshTokenValidTime);
			log.info("createTokensFromUserPrincipal | key: {}", key);
		}
		return token;
	}

	/**
	 * 사용자 아이디로 토큰 생성(회원 가입 유저를 위한)
	 */
	public void createTokensFromUserId(UUID userId, String tokenType) {
		Date currentTime = new Date();
		Date expirationTime = calcTokenExpirationTime(currentTime, tokenType);
		String token = Jwts.builder()
				.setSubject(userId.toString())
				.claim(Utils.ROLE, Role.ROLE_USER)
				.setIssuedAt(currentTime)
				.setExpiration(expirationTime)
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();

		if (tokenType.equals(Utils.REFRESH_TOKEN)) {
			String key = Utils.RT_COLON + Encoders.BASE64.encode(uuidToByteArray(userId));
			redisUtil.setDataExpire(key, token, refreshTokenValidTime);
			log.info("createTokensFromAuthentication | key: {}", key);
		}
	}

	/**
	 * 인증 정보 조회
	 */
	public Authentication getAuthentication(String token) {
		UserPrincipal userPrincipal = null;
		Claims claims = parseClaims(token);

		log.info("getAuthentication | claims: {}", claims);

		if (claims.get(Utils.ROLE) == null) {
			log.info("getAuthentication | claims.get(Utils.ROLE): {}", claims.get(Utils.ROLE));
			throw new ForbiddenException("해당 기능을 요청할 권한이 없습니다.");
		}
		if (claims.get(Utils.ROLE).equals(Utils.ROLE_ANONYMOUS)) {
			// 익명 권한 (카카오 로그인 성공 후, 서비스 회원가입 중)이면 토큰 정보 꺼내와서 사용
			User user = User.builder()
					.userId(getUserIdFromClaims(claims))
					.role(getRoleFromClaims(claims))
					.build();
			userPrincipal = UserPrincipal.createUserPrincipal(user);

			log.info(
					"getAuthentication | claims.get(Utils.ROLE).equals(Utils.ROLE_GUEST): {}",
					claims.get(Utils.ROLE).equals(Utils.ROLE_ANONYMOUS)
			);

		} else if (claims.get(Utils.ROLE).equals(Utils.ROLE_USER)) {
			// 유저 권한 (카카로 로그인, 서비스 회원가입 완료)이면 db에서 조회해서 사용
			userPrincipal = userRepository.findById(getUserIdFromClaims(claims))
					.map(UserPrincipal::createUserPrincipal)
					.orElseThrow(() -> new ForbiddenException("해당 기능을 요청할 권한이 없습니다."));

			log.info(
					"getAuthentication | claims.get(Utils.ROLE).equals(Utils.ROLE_USER): {}",
					claims.get(Utils.ROLE).equals(Utils.ROLE_USER)
			);
		}
		log.info("userPrincipal: {}", userPrincipal);

		assert userPrincipal != null;
		return new UsernamePasswordAuthenticationToken(
				userPrincipal,
				token,
				userPrincipal.getAuthorities()
		);
	}

	/**
	 * 토큰 유효성 검사 return 유효 토큰 여부
	 */
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build()
					.parseClaimsJws(token);
			log.info("validateToken | claims.getBody().getExpiration():{}",
					claims.getBody().getExpiration());
			log.info("validateToken | claims.getBody().getExpiration().after(new Date()):{}",
					claims.getBody().getExpiration().after(new Date()));

			return claims.getBody().getExpiration().after(new Date());
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}

	/**
	 * Jwt 복호화
	 */
	public Claims parseClaims(String token) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key).build()
					.parseClaimsJws(token)
					.getBody();
		} catch (ExpiredJwtException expiredJwtException) {
			return expiredJwtException.getClaims();
		}
	}

	/**
	 * Jwt 복호화 후 userId 가져오기
	 */
	public UUID getUserIdFromJwt(String token) {
		try {
			return UUID.fromString(Jwts.parserBuilder()
					.setSigningKey(key).build()
					.parseClaimsJws(token)
					.getBody()
					.getSubject());
		} catch (ExpiredJwtException expiredJwtException) {
			return UUID.fromString(expiredJwtException.getClaims().getSubject());
		}
	}

	/**
	 * claims에서 userId 가져오기
	 */
	public UUID getUserIdFromClaims(Claims claims) {
		return UUID.fromString(claims.getSubject());
	}

	/**
	 * claims에서 role 가져오기
	 */
	public Role getRoleFromClaims(Claims claims) {
		return Role.valueOf(claims.get(Utils.ROLE).toString());
	}

	/**
	 * Jwt 복호화 후 token 만료 시간 가져오기
	 */
	public long getExpiration(String token) {
		try {
			long expiration = Jwts.parserBuilder()
					.setSigningKey(key).build()
					.parseClaimsJws(token)
					.getBody()
					.getExpiration()
					.getTime();
			long now = new Date().getTime();
			return expiration - now;
		} catch (ExpiredJwtException expiredJwtException) {
			return expiredJwtException.getClaims().getExpiration().getTime();
		}
	}

	/**
	 * 토큰 재발급
	 */
	public JwtDTO reissue(String token) {
		Authentication authentication = getAuthentication(token);
		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
		UUID userId = userPrincipal.getUserId();
		String key = Utils.RT_COLON + Encoders.BASE64.encode(uuidToByteArray(userId));
		String refreshToken = redisUtil.getData(key);
		if (refreshToken == null) {
			throw new RefreshTokenNotFoundException("Refresh Token이 존재하지 않습니다.");
		}
		String accessToken = createTokensFromUserPrincipal(userPrincipal, Utils.ACCESS_TOKEN);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return JwtDTO.builder()
				.token(accessToken)
				.build();
	}

	public Date calcTokenExpirationTime(Date currentTime, String tokenType) {
		Date expirationTime = null;

		if (tokenType.equals(Utils.ACCESS_TOKEN)) {
			expirationTime = new Date(currentTime.getTime() + accessTokenValidTime);
		} else if (tokenType.equals(Utils.REFRESH_TOKEN)) {
			expirationTime = new Date(currentTime.getTime() + refreshTokenValidTime);
		}
		return expirationTime;
	}

	public static byte[] uuidToByteArray(UUID uuid) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(16);
		byteBuffer.putLong(uuid.getMostSignificantBits());
		byteBuffer.putLong(uuid.getLeastSignificantBits());
		return byteBuffer.array();
	}
}
