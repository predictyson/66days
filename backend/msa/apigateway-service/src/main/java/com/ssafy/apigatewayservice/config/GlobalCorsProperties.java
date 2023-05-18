package com.ssafy.apigatewayservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties("spring.cloud.gateway.globalcors")
public class GlobalCorsProperties {

    private final Map<String, CorsConfiguration> corsConfigurations = new LinkedHashMap<>();

    public Map<String, CorsConfiguration> getCorsConfigurations() {
        return corsConfigurations;
    }

}