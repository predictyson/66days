package com.ssafy._66days.challenge.model.service;

import com.ssafy._66days.badge.model.entity.Badge;
import com.ssafy._66days.badge.model.repository.BadgeRepository;
import com.ssafy._66days.challenge.model.dto.requestDTO.MyChallengeRequestDTO;
import com.ssafy._66days.challenge.model.entity.Challenge;
import com.ssafy._66days.challenge.model.entity.MyChallenge;
import com.ssafy._66days.challenge.model.reposiotry.ChallengeRepository;
import com.ssafy._66days.challenge.model.reposiotry.MyChallengeRepository;
import com.ssafy._66days.global.util.CheckUserUtil;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("MyChallengeService")
@Transactional(readOnly = true)
public class MyChallengeService {
    private final MyChallengeRepository myChallengeRepository;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final BadgeRepository badgeRepository;



    public MyChallengeService(
            UserRepository userRepository,
            MyChallengeRepository myChallengeRepository,
            ChallengeRepository challengeRepository,
            BadgeRepository badgeRepository
    ) {
        this.myChallengeRepository = myChallengeRepository;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.badgeRepository = badgeRepository;
    }

    @Transactional
    public boolean createMyChallenge(UUID userId, MyChallengeRequestDTO myChallengeRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Challenge challenge = challengeRepository.findById(myChallengeRequestDTO.getChallengeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다"));
        Long badgeId = challenge.getBadge().getBadgeId();
        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new IllegalArgumentException("관련된 뱃지가 없습니다"));
        String imagePath = badge.getImagePath();
        LocalDateTime startDay = LocalDateTime.now();
        LocalDateTime endDay = startDay.plusDays(66);
        String state = "ACTIVATE";

        MyChallenge myChallege = MyChallenge.builder()
                .user(user)
                .challenge(challenge)
                .challengeName(myChallengeRequestDTO.getChallengeName())
                .content(myChallengeRequestDTO.getContent())
                .startAt(startDay)
                .endAt(endDay)
                .state(state)
                .build();
        MyChallenge savedMyChallenge = myChallengeRepository.save(myChallege);
        if (savedMyChallenge == null) {
            return false;
        }
        return true;
    }

}
