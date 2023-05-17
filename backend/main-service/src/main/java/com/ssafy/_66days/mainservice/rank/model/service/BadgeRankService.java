package com.ssafy._66days.mainservice.rank.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy._66days.mainservice.rank.model.repository.BadgeRankRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BadgeRankService {

	private final BadgeRankRepository badgeRankRepository;
}
