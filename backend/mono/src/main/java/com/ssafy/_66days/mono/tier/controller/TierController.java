package com.ssafy._66days.mono.tier.controller;

import com.ssafy._66days.mono.tier.model.service.TierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tier")
public class TierController {

	private final TierService tierService;
}
