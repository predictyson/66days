package com.ssafy._66days.challenge.model.service;

import com.ssafy._66days.animal.model.entity.Animal;
import com.ssafy._66days.badge.model.entity.Badge;
import com.ssafy._66days.badge.model.repository.BadgeRepository;
import com.ssafy._66days.challenge.model.dto.requestDTO.MyChallengeRequestDTO;
import com.ssafy._66days.challenge.model.dto.responseDTO.AvailableMyChallengeResponseDTO;
import com.ssafy._66days.challenge.model.entity.Challenge;
import com.ssafy._66days.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.challenge.model.entity.GroupChallengeMember;
import com.ssafy._66days.challenge.model.entity.MyChallenge;
import com.ssafy._66days.challenge.model.reposiotry.ChallengeRepository;
import com.ssafy._66days.challenge.model.reposiotry.GroupChallengeMemberRepository;
import com.ssafy._66days.challenge.model.reposiotry.GroupChallengeRepository;
import com.ssafy._66days.challenge.model.reposiotry.MyChallengeRepository;
import com.ssafy._66days.global.util.CheckUserUtil;
import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.group.model.entity.GroupMember;
import com.ssafy._66days.group.model.repository.GroupRepository;
import com.ssafy._66days.tier.model.entity.Tier;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("MyChallengeService")
@Transactional(readOnly = true)
public class MyChallengeService {
    private final MyChallengeRepository myChallengeRepository;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final BadgeRepository badgeRepository;
    private final GroupChallengeRepository groupChallengeRepository;
    private final GroupRepository groupRepository;
    private final GroupChallengeMemberRepository groupChallengeMemberRepository;



    public MyChallengeService(
            UserRepository userRepository,
            MyChallengeRepository myChallengeRepository,
            ChallengeRepository challengeRepository,
            BadgeRepository badgeRepository,
            GroupChallengeRepository groupChallengeRepository,
            GroupRepository groupRepository,
            GroupChallengeMemberRepository groupChallengeMemberRepository
    ) {
        this.myChallengeRepository = myChallengeRepository;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.badgeRepository = badgeRepository;
        this.groupChallengeRepository = groupChallengeRepository;
        this.groupRepository = groupRepository;
        this.groupChallengeMemberRepository = groupChallengeMemberRepository;
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
    // 내가 개인 혹은 그룹에서 하고 있는 챌린지아이디를 담을 배열을 만든다
    // userId로 내가 하고 있는 챌린지들의 챌린지 아이디를 찾는다
    // 넘어온 groupIdList로 activated인 챌린지를 찾고 그중 그룹 챌린지 참여자에 userId가 있는 것의 챌린지 아이디를 찾는다
    // 개인, 그룹에서 하고 있는 챌린지 아이디를 배열에 담고
    // 챌린지 5개 목록 미리 준비
    // 하고 있는 챌린지 아이디들은 응답DTO에 boolean값을 false호 넣고 반환
    public List<AvailableMyChallengeResponseDTO> getAvailableMyChallengeList(UUID userId, List<Long> groupIdList) {



        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        List<Long> temp = new ArrayList<>();    // 하고 있는 챌린지 id 담을 배열
        List<Challenge> challlengeList = challengeRepository.findAll();                       // 챌린지 메타 데이터(5개)


        List<GroupChallengeMember> GCMList = groupChallengeMemberRepository.findByUser(user); // 내가 참여중인 그룹 챌린지들
        if (!GCMList.isEmpty()) {
            for (int i = 0; i < GCMList.size(); i++) {

                GroupChallenge groupChallenge = groupChallengeRepository.findById(GCMList.get(i).getGroupChallenge().getGroupChallengeId()) // 그룹챌린지를 찾아서
                        .orElseThrow(() -> new IllegalArgumentException("알수없는 에러"));
                if (groupChallenge.getState() == "ACTIVATED") {                                 // 진행중인 챌린지라면
                    Long challengeId = groupChallenge.getChallenge().getChallengeId();          // 그것의 챌린지 아이디를 찾는다
                    temp.add(challengeId);                                                      // 그룹에서 참여하고 있는 challenge들의 id값을 temp에 저장
                }
            }
        }

        List<MyChallenge> myChallengeList = myChallengeRepository.findByUser(user);             // 개인 챌린지들을 찾아서
        if (!myChallengeList.isEmpty()) {
            for (int i = 0; i < myChallengeList.size(); i++) {
                Long challengeId = myChallengeList.get(i).getChallenge().getChallengeId();      // 그것들의 챌린지 아이디를 찾는다
                temp.add(challengeId);                                                          // 개인이 하고 잇는 챌린지들의 id를 temp에 저장
            }
        }

        for (int i = 0; i < temp.size(); i++) {
//            temp에 있는 challenge들은 isavailable 값 false로, 없는 것들은 true로 해서 responseDTO list로 만들어 반환
        }
        return null;
//        if (groupIdList.size() > 0) {
//
//            for (int i = 0; i < groupIdList.size(); i++) {
//                Long groupId = groupIdList.get(i);
//                Group group = groupRepository.findById(groupId)
//                        .orElseThrow(() -> new IllegalArgumentException(""));
//                List<Long> challengeLId = groupChallengeRepository.findByGroupAndUser(userId, groupId);
//            }
//        }
//        if (myChallengeList.size() != 0) {
//
//        }
    }
}
