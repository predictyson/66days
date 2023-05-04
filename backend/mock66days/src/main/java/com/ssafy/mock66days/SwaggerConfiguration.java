package com.ssafy.mock66days;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    // Swagger - API 기본 설정
    private String API_VERSION = "0.1";
    private String API_TITLE = "자율PJT 66days! MOCK API Documentation";
    private String API_DESCRIPTION = "SSAFY 자율 PJT 66days API";

    public Docket getDocket(String groupName, boolean defaultResponseMessage,
                            String basePackage) {
        return new Docket(DocumentationType.OAS_30).groupName(groupName)
                .useDefaultResponseMessages(defaultResponseMessage) // Swagger 에서 제공해주는 기본 응답 코드 표시 여부
                .apiInfo(apiInfo()) // apiInfo정보
//                .securityContexts(Arrays.asList(securityContext()))
//                .securitySchemes(Arrays.asList(apiKey()))
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.ssafy." + basePackage + ".controller"))
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.mock66days." + basePackage + ".controller"))
                .paths(PathSelectors.any()) // 아무 경로나 가능
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(API_TITLE).description(API_DESCRIPTION).version(API_VERSION).build();
    }

    @Bean
    public Docket page() {
        return getDocket("Page", true, "page");
    }
    public Docket group() {
        return getDocket("Group", true, "group");
    }

}