package com.ssafy._66days.mainservice.tier.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy._66days.mainservice.tier.model.service.TierService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tier")
public class TierController {

	private final TierService tierService;
}
