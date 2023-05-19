package com.ssafy._66days.mono.user.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
    private String name;
    private String email;
    private ProviderType social;
    
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, ProviderType social) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.social = social;
    }
    
    public static OAuthAttributes of(String socialName, String userNameAttributeName, Map<String, Object> attributes){        
        if("kakao".equals(socialName)){
            return ofKakao(userNameAttributeName, attributes);
        }
        return null;
    }
    

    @SuppressWarnings("unchecked")
	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
    	Map<String, Object> response = (Map<String, Object>)attributes.get("kakao_account");
    	Map<String, Object> profile = (Map<String, Object>) response.get("profile");
        
    	Optional<String> email = Optional.ofNullable((String) response.get("email"));
    	String emailStr;
    	if (email.isPresent()) {
    		emailStr = email.orElse(null);
    	} else {
    		emailStr = "none";
    	}
        return OAuthAttributes.builder()
                .name((String) profile.get("nickname"))
                .email(emailStr)
                .social(ProviderType.KAKAO)
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .build();
    }
//    public User toEntity(){
//        return User.builder()
//                .nickname(name)
//                .email(email)
//                .social(social)
//                .roleType(RoleType.USER)
//                .build();
//    }
}