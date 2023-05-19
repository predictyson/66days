package com.ssafy._66days.mainservice.tier.model.service;

import com.ssafy._66days.mainservice.tier.model.repository.TierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TierService {

	private final TierRepository tierRepository;
}
