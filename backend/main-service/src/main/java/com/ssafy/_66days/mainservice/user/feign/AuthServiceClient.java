package com.ssafy._66days.mainservice.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

//@FeignClient(name = "auth-service", url = "13.124.253.176:8000")
@FeignClient(name = "auth-service", url = "localhost:8000")
public interface AuthServiceClient {

    @GetMapping("/auth-service/user/uuid/{uuid}")
    ResponseEntity<UUID> extractUUID(@PathVariable("uuid") UUID uuid);
}
