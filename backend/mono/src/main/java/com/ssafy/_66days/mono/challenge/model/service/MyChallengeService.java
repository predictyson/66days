package com.ssafy._66days.mono.challenge.model.service;

import com.ssafy._66days.mono.badge.model.repository.BadgeRepository;
import com.ssafy._66days.mono.challenge.model.dto.MyChallengeHistoryDTO;
import com.ssafy._66days.mono.challenge.model.dto.requestDTO.MyChallengeRequestDTO;
import com.ssafy._66days.mono.challenge.model.dto.requestDTO.StreakRequestDTO;
import com.ssafy._66days.mono.challenge.model.dto.responseDTO.AvailableMyChallengeResponseDTO;
import com.ssafy._66days.mono.challenge.model.dto.responseDTO.MyChallengeDetailResponseDTO;
import com.ssafy._66days.mono.challenge.model.dto.responseDTO.MyChallengeResponseDTO;
import com.ssafy._66days.mono.challenge.model.entity.Challenge;
import com.ssafy._66days.mono.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mono.challenge.model.entity.GroupChallengeMember;
import com.ssafy._66days.mono.challenge.model.entity.MyChallenge;
import com.ssafy._66days.mono.challenge.model.entity.mongodb.PersonalChallengeLog;
import com.ssafy._66days.mono.challenge.model.reposiotry.*;
import com.ssafy._66days.mono.group.model.repository.GroupRepository;
import com.ssafy._66days.mono.user.model.entity.User;
import com.ssafy._66days.mono.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Service("MyChallengeService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MyChallengeService {
    private final MyChallengeRepository myChallengeRepository;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final BadgeRepository badgeRepository;
    private final GroupChallengeRepository groupChallengeRepository;
    private final GroupRepository groupRepository;
    private final GroupChallengeMemberRepository groupChallengeMemberRepository;
    private final PersonalChallengeLogRepository personalChallengeLogRepository;


    @Transactional
    public boolean createMyChallenge(UUID userId, MyChallengeRequestDTO myChallengeRequestDTO) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Challenge challenge = challengeRepository.findById(myChallengeRequestDTO.getChallengeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다"));
        if (myChallengeRequestDTO.getChallengeName() == null || myChallengeRequestDTO.getChallengeName().trim().isEmpty()) {
            throw new IllegalArgumentException("챌린지 명을 작성해 주시기 바랍니다");
        }
        if (myChallengeRequestDTO.getContent() == null || myChallengeRequestDTO.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("챌린지 설명을 작성해 주시기 바랍니다");
        }
        // 중복된 챌린지를 하고 있는지 확인
        List<Long> temp = new ArrayList<>();                                                  // 하고 있는 챌린지 id 담을 배열
        List<GroupChallengeMember> groupChallengeMemberList = groupChallengeMemberRepository.findByUser(user); // 내가 참여중인 그룹 챌린지들
        if (!groupChallengeMemberList.isEmpty()) {
            for (int i = 0; i < groupChallengeMemberList.size(); i++) {

                GroupChallenge groupChallenge = groupChallengeRepository.findById(groupChallengeMemberList.get(i).getGroupChallenge().getGroupChallengeId()) // 그룹챌린지를 찾아서
                        .orElseThrow(() -> new IllegalArgumentException("알수없는 에러"));
                if (groupChallenge.getState().equals("ACTIVATED")) {                                 // 진행중인 챌린지라면
                    Long challengeId = groupChallenge.getChallenge().getChallengeId();          // 그것의 챌린지 아이디를 찾는다
                    temp.add(challengeId);                                                      // 그룹에서 참여하고 있는 challenge들의 id값을 temp에 저장
                }
            }
        }
        String status = "ACTIVATED";
        List<MyChallenge> myChallengeList = myChallengeRepository.findByUserAndState(user, status);             // 개인 챌린지들을 찾아서
        if (!myChallengeList.isEmpty()) {
            for (int i = 0; i < myChallengeList.size(); i++) {
                Long challengeId = myChallengeList.get(i).getChallenge().getChallengeId();      // 그것들의 챌린지 아이디를 찾는다
                temp.add(challengeId);                                                          // 개인이 하고 dlT는 챌린지들의 id를 temp에 저장
            }
        }
        if (temp.contains(challenge.getChallengeId())) {
            throw new IllegalArgumentException("이미 참여 중인 챌린지 입니다");

        }

        LocalDateTime startAt = now();                                            // 오늘날짜 시작
        LocalDateTime endAt = startAt.plusDays(66);                                            // 종료날짜 = 시작날짜 + 66일
        String state = "ACTIVATED";                                                              // 상태 활성화

        MyChallenge myChallenge = MyChallenge.builder()
                .user(user)
                .challenge(challenge)
                .challengeName(myChallengeRequestDTO.getChallengeName())
                .content(myChallengeRequestDTO.getContent())
                .startAt(startAt)
                .endAt(endAt)
                .state(state)
                .build();
        return myChallengeRepository.save(myChallenge) != null;
    }

    public List<MyChallengeResponseDTO> getMyChallenges(UUID userId) {

        User user = userRepository.findById(userId)                         // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        
        String state = "ACTIVATED";                                         // 개인 챌린지 중 진행중인 것만 받아온다 
        List<MyChallenge> myChallengeList = myChallengeRepository.findByUserAndState(user, state);
        
        List<MyChallengeResponseDTO> myChallengeResponseDTOList = new ArrayList<>();
        if (!myChallengeList.isEmpty()) {                                      // 진행중인 챌린지가 있다면
            myChallengeResponseDTOList = myChallengeList.stream()
                    .map(myChanllenge -> MyChallengeResponseDTO.of(myChanllenge))
                    .collect(Collectors.toList());                          // 각 챌린지를 DTO로 변환 후 반환
        }
        return myChallengeResponseDTOList;
    }

    // 내가 개인 혹은 그룹에서 하고 있는 챌린지아이디를 담을 배열을 만든다
    // userId로 내가 하고 있는 챌린지들의 챌린지 아이디를 찾는다
    // 그룹 챌린지 참여자에서 내 userId로 찾는다
    // 있다면 그룹 챌린지 ID로 그룹챌린지에서 챌린지 아이디를 찾는다
    // 개인, 그룹에서 하고 있는 챌린지 아이디를 배열에 담고
    // 1부터 챌린지 갯수만큼 순회하면서
    // 내가하고 있는 챌린지id가 나오면 state응 false로 DTO 생성해서 담고 아니면 true로 담는다
    // 결론적으로 챌린지id 1번부터 순서가 보장된 DTO 리스트가 반환된다
    public List<AvailableMyChallengeResponseDTO> getAvailableMyChallengeList(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        List<Long> temp = new ArrayList<>();                                                  // 하고 있는 챌린지 id 담을 배열
        List<Challenge> challlengeList = challengeRepository.findAll();                       // 챌린지 메타 데이터(5개)


        List<GroupChallengeMember> groupChallengeMemberList = groupChallengeMemberRepository.findByUser(user); // 내가 참여중인 그룹 챌린지들
        if (!groupChallengeMemberList.isEmpty()) {
            for (int i = 0; i < groupChallengeMemberList.size(); i++) {

                GroupChallenge groupChallenge = groupChallengeRepository.findById(groupChallengeMemberList.get(i).getGroupChallenge().getGroupChallengeId()) // 그룹챌린지를 찾아서
                        .orElseThrow(() -> new IllegalArgumentException("알수없는 에러"));
                if (groupChallenge.getState().equals("ACTIVATED")) {                                 // 진행중인 챌린지라면
                    Long challengeId = groupChallenge.getChallenge().getChallengeId();          // 그것의 챌린지 아이디를 찾는다
                    temp.add(challengeId);                                                      // 그룹에서 참여하고 있는 challenge들의 id값을 temp에 저장
                }
            }
        }

        List<MyChallenge> myChallengeList = myChallengeRepository.findByUser(user);             // 개인 챌린지들을 찾아서
        if (!myChallengeList.isEmpty()) {
            for (int i = 0; i < myChallengeList.size(); i++) {
                String isActivated = myChallengeList.get(i).getState();
                if (isActivated.equals("ACTIVATED")) {
                    Long challengeId = myChallengeList.get(i).getChallenge().getChallengeId();      // 그것들의 챌린지 아이디를 찾는다
                    temp.add(challengeId);                                                          // 개인이 하고 있는 챌린지들의 id를 temp에 저장
                }

            }
        }

        List<AvailableMyChallengeResponseDTO> availableMyChallengeResponseDTOList = new ArrayList<>(); // 가능/불가능 챌린지를 담을 배열
        int challengeNumber = challlengeList.size();                                                // 전체 챌린지 갯수
        for (Long i = 1L; i <= challengeNumber; i++) {                                              // 전체 챌린지 갯수만큼 순회 1번부터
            Challenge challenge = challengeRepository.findById(i)                                   // 해당 번호의 챌린지를 가져온다
                    .orElseThrow(() -> new IllegalArgumentException("유효한 챌린지가 아닙니다"));
            if (temp.contains(i)) {                                                                 // 해당 챌린지번호가 temp에 있으면, 즉 유저가 하고 있는 챌린지라면
                AvailableMyChallengeResponseDTO availableMyChallengeResponseDTO = AvailableMyChallengeResponseDTO.of(challenge, false); // 응답DTO에 available=false로 넣는다
                availableMyChallengeResponseDTOList.add(availableMyChallengeResponseDTO);
            } else {                                                                                // 해당 챌린지번호가 temp에 없으면, 즉 유저가 하고 있지 않는 챌린지라면
                AvailableMyChallengeResponseDTO availableMyChallengeResponseDTO = AvailableMyChallengeResponseDTO.of(challenge, true);  // 응답DTO에 available=true로 넣는다
                availableMyChallengeResponseDTOList.add(availableMyChallengeResponseDTO);
            }
        }
        return availableMyChallengeResponseDTOList;    // 최종으로 모아서 반환

    }

    // 개인 챌린지 상세페이지
    // 챌린지 이름, 설명, 이전 챌린지 히스토리 정보 반환
    public MyChallengeDetailResponseDTO getMyChallengeDetail(UUID userId, Long myChallengeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        String fail = "FAILED";
        String success = "SUCCESSFUL";
        List<MyChallenge> myFailChallenges = myChallengeRepository.findByState(fail);       // 실패했던 챌린지 찾아오기
        List<MyChallenge> mySuccessChallenges = myChallengeRepository.findByState(success); // 성공했던 챌린지 찾아오기
        
        List<MyChallengeHistoryDTO> myChallengeHistoryDTOs = new ArrayList<>();             // 챌린지 히스토리 받을 배열
        for (MyChallenge mychallenge : myFailChallenges) {                                  // 실패챌린지 순회
            MyChallengeHistoryDTO myChallengeHistoryDTO = MyChallengeHistoryDTO.of(mychallenge, false); // 실패챌린지 DTO로 변환
            myChallengeHistoryDTOs.add(myChallengeHistoryDTO);                              // 챌린지 히스토리에 담는다
        }
        for (MyChallenge mychallenge : mySuccessChallenges) {                               // 성공챌린지 순회
            MyChallengeHistoryDTO myChallengeHistoryDTO = MyChallengeHistoryDTO.of(mychallenge, true);  // 성공챌린지DTO로 변환
            myChallengeHistoryDTOs.add(myChallengeHistoryDTO);                              // 챌린지 히스토리에 담는다
        }

        MyChallenge myChallenge = myChallengeRepository.findById(myChallengeId)             // 해당 챌린지 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다"));
        return MyChallengeDetailResponseDTO.of(myChallenge, myChallengeHistoryDTOs);                // 해당 챌린지의 정보와 챌린지 히스토리로 상세페이지 정보 DTO 만들어서 반환
    }

    @Transactional

    public boolean checkPrivateStreak(
            UUID userId,
            Long myChallengeId
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));    // 유저 유무 체크
        MyChallenge myChallenge = myChallengeRepository.findByMyChallengeIdAndState(myChallengeId, "ACTIVATED")
                .orElseThrow(() -> new IllegalArgumentException("진행 중인 챌린지가 아닙니다"));  // 개인 챌린지 존재 유무 체크
        if (!myChallenge.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인의 개인 챌린지가 아닙니다");                 // 해당 개인 챌린지가 해당 유저의 것인지 체크
        }

        LocalDate time = LocalDate.now(ZoneOffset.UTC);                                                   // 오늘 날짜
        PersonalChallengeLog todayPersonalChallengeLog = personalChallengeLogRepository.findByMyChallengeIdAndTime(myChallengeId, time); // 오늘 날짜 스트릭 로그 조회
        if (todayPersonalChallengeLog != null) {                                            // 스트릭 존재한다면
            throw new IllegalArgumentException("이미 금일 스트릭을 채우셨습니다");                // 예외처리
        }
        PersonalChallengeLog personalChallengeLog = PersonalChallengeLog.builder()          // 금일 스트릭 로그 없다면 몽고DB에 로그 작성
                .myChallengeId(myChallengeId)
                .time(time)
                .build();

        return personalChallengeLogRepository.save(personalChallengeLog) != null;
    }
}

