package com.ssafy._66days.mono.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;


@Configuration
public class SwaggerConfig {

    // Swagger - API 기본 설정
    private String API_VERSION = "0.1";
    private String API_TITLE = "자율PJT 66days! API Documentation";
    private String API_DESCRIPTION = "SSAFY 자율 PJT 66days API";

    public Docket getDocket(String groupName, boolean defaultResponseMessage,
                            String basePackage) {
        return new Docket(DocumentationType.OAS_30).groupName(groupName)
                .useDefaultResponseMessages(defaultResponseMessage) // Swagger 에서 제공해주는 기본 응답 코드 표시 여부
                .apiInfo(apiInfo()) // apiInfo정보
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy._66days.mono." + basePackage + ".controller"))
                .paths(PathSelectors.any()) // 아무 경로나 가능
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(API_TITLE).description(API_DESCRIPTION).version(API_VERSION).build();
    }
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope =
                new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes =
                new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("Authorization", authorizationScopes));
    }

    @Bean
    public Docket user() {
        return getDocket("User", true, "user");
    }
    @Bean
    public Docket article() {
        return getDocket("Article", true, "article");
    }
    @Bean
    public Docket group() {
        return getDocket("Group", true, "group");
    }
    @Bean
    public Docket item() { return getDocket("Item", true, "item"); }
    @Bean
    public Docket challenge() { return getDocket("Challenge", true, "challenge"); }
    @Bean
    public Docket page() { return getDocket("Page", true, "page"); }

}