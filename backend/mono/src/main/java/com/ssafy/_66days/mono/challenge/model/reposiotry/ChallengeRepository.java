package com.ssafy._66days.mono.challenge.model.reposiotry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy._66days.mono.challenge.model.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
