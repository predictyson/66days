package com.ssafy._66days.mainservice.rank.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy._66days.mainservice.rank.model.repository.ExpRankRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExpRankService {

	private final ExpRankRepository expRankRepository;
}
