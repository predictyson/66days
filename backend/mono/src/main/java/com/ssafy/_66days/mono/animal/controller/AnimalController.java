package com.ssafy._66days.mono.animal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy._66days.mono.animal.model.service.AnimalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animal")
public class AnimalController {

	private final AnimalService animalService;
}
