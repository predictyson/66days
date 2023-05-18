package com.ssafy._66days.mono.challenge.model.service;

import com.ssafy._66days.mono.challenge.model.dto.GroupChallengeMemberDTO;
import com.ssafy._66days.mono.challenge.model.dto.requestDTO.GroupChallengeRequestDTO;
import com.ssafy._66days.mono.challenge.model.dto.responseDTO.*;
import com.ssafy._66days.mono.challenge.model.entity.*;
import com.ssafy._66days.mono.challenge.model.entity.mongodb.GroupChallengeLog;
import com.ssafy._66days.mono.challenge.model.reposiotry.*;
import com.ssafy._66days.mono.group.model.entity.Group;
import com.ssafy._66days.mono.group.model.entity.GroupMember;
import com.ssafy._66days.mono.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.mono.group.model.repository.GroupRepository;
import com.ssafy._66days.mono.user.model.entity.User;
import com.ssafy._66days.mono.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Service("GroupChallengeService")
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class GroupChallengeService {
    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupRepository groupRepository;
    private final ChallengeRepository challengeRepository;
    private final GroupChallengeRepository groupChallengeRepository;
    private final GroupChallengeMemberRepository groupChallengeMemberRepository;
    private final GroupChallengeLogRepository groupChallengeLogRepository;
    private final MyChallengeRepository myChallengeRepository;
    private final GroupChallengeApplicationRepository groupChallengeApplicationRepository;

    @Transactional
    public boolean createGroupChallenge(
            UUID userId,
            Long groupId,
            GroupChallengeRequestDTO groupChallengeRequestDTO
    ) {
        User user = userRepository.findById(userId)                         // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));
        GroupMember groupMember = groupMemberRepository.findByUserAndGroupAndIsDeleted(user, group, false)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속한 유저가 아닙니다"));
        Challenge challenge = challengeRepository.findById(groupChallengeRequestDTO.getChallengeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지 입니다"));

        if (!groupMember.getAuthority().equals("OWNER") && !groupMember.getAuthority().equals("MANAGER")) {
            throw new IllegalArgumentException("챌린지를 생성할 권한이 없습니다");
        }
        if (groupChallengeRequestDTO.getChallengeName() == null) {
            throw new IllegalArgumentException("챌린지 이름을 작성해주십시오");
        }
        if (groupChallengeRequestDTO.getMaxMemberCount() < 2 || groupChallengeRequestDTO.getMaxMemberCount() > 66) {
            throw new IllegalArgumentException("챌린지 참가 인원은 2명부터 66명까지 가능합니다");
        }
        if (groupChallengeRequestDTO.getContent() == null) {
            throw new IllegalArgumentException("챌린지 설명을 작성해주십시오");
        }
        if (groupChallengeRequestDTO.getStartAt() == null) {
            throw new IllegalArgumentException("챌린지 시작 날짜를 입력해 주십시오");
        }

        Date startDate = groupChallengeRequestDTO.getStartAt();
        LocalDateTime startAt = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());  // 입력받은 시작날짜를 LocalDateTime으로 변환
        LocalDateTime endAt = startAt.plusDays(66);                                                      // 종료날짜 = 시작날짜 + 66일

        LocalDateTime today = now();          // 오늘 날짜

        LocalDate date1 = startAt.toLocalDate();
        LocalDate date2 = today.toLocalDate();

        int comparison = date1.compareTo(date2);

        String state = "";
        if (comparison == 0) {
            state = "ACTIVATED";
        } else if (comparison < 0) {
            throw new IllegalArgumentException("과거는 선택할 수 없습니다");
        } else {
            state = "WAITING";
        }

        List<GroupChallenge> groupChallenges = groupChallengeRepository.findByGroupAndChallengeAndStateIn(group, challenge, Arrays.asList("ACTIVATED", "WAITING")); // 현재 진행 중인 챌린지가 있는지 찾아온다
        if (ChronoUnit.DAYS.between(today, startAt) > 30) {                                              //  시작날짜가 오늘 날짜에서 30일 초과한 날짜인지 확인
            throw new IllegalArgumentException("챌린지는 최대 30일 이내에 시작해야 합니다");
        }
        if (groupChallenges != null) {
            for (int i = 0; i < groupChallenges.size(); i++) {
                GroupChallenge groupChallenge = groupChallenges.get(i);
                if (groupChallenge.getEndAt().isAfter(startAt)) {                                           // 같은 챌린지가 해당 그룹내에서 진행중인지 확인(시작날짜가 진행 중 챌린지 endAt보다 전인지 확인)
                    throw new IllegalArgumentException("동일한 챌린지가 그룹 내에서 진행 중인 날짜에는 챌린지를 시작할 수 없습니다");
                }
            }
        }


        String status = state;                                                                           // 새로 시작할 챌린지 상태값
        int availableFreezingCount = 0;                                                                  // 사용가능 프리징 수 기본값
        GroupChallenge newGroupChallenge = GroupChallenge.builder()
                .group(group)
                .challenge(challenge)
                .challengeName(groupChallengeRequestDTO.getChallengeName())
                .content(groupChallengeRequestDTO.getContent())
                .startAt(startAt)
                .endAt(endAt)
                .state(status)
                .availableFreezingCount(availableFreezingCount)
                .maxMemberCount(groupChallengeRequestDTO.getMaxMemberCount())
                .build();
        return groupChallengeRepository.save(newGroupChallenge) != null;
    }

    public List<AvailableGroupChallengeResponseDTO> getAvailableGroupChallengeList(
            UUID userId,
            Long groupId
    ) {
        User user = userRepository.findById(userId)                         // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)                     // 그룹 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));
        GroupMember groupMember = groupMemberRepository.findByUserAndGroupAndIsDeleted(user, group, false)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속한 유저가 아닙니다"));
        if (!groupMember.getAuthority().equals("OWNER") && !groupMember.getAuthority().equals("MANAGER")) { // 그룹장이나 매니저가 아니라면 챌린지를 만들 수 없다
            throw new IllegalArgumentException("챌린지 생성 권한이 없습니다");
        }

        List<Challenge> challengeList = challengeRepository.findAll();          // 챌린지 메타데이터 받아오기

        List<AvailableGroupChallengeResponseDTO> AvailableGroupChallengeResponseDTOList = challengeList.stream()    // ResponseDTO로 변환해서 List에 담는다
                .map(challenge -> AvailableGroupChallengeResponseDTO.of(challenge))
                .collect(Collectors.toList());

        return AvailableGroupChallengeResponseDTOList;
    }

    public List<GroupChallengeResponseDTO> getGroupChallenges(
            UUID userId,
            Long groupId
    ) {
        // 내가 그룹에 속한 사람인지 확인
        User user = userRepository.findById(userId)                         // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)                     // 그룹 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));
        GroupMember groupMember = groupMemberRepository.findByUserAndGroupAndIsDeleted(user, group, false)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속한 유저가 아닙니다"));

        // 그룹아이디로 진행중인 챌린지와 진행 예정인 챌린지 리스트를 받아온다
        List<GroupChallenge> groupChallengeList = groupChallengeRepository.findByGroupAndStateIn(group, Arrays.asList("ACTIVATED", "WAITING"));
        List<GroupChallengeResponseDTO> groupChallengeResponseDTOList = new ArrayList<>();  // 그룹 챌린지 반환 리스트
        for (int i = 0; i < groupChallengeList.size(); i++) {
            GroupChallenge groupChallenge = groupChallengeList.get(i);                      // 각 그룹 챌린지 객체

            List<String> challengeMemberImagePathDTOList = new ArrayList<>();               // 참여자들의 프로필 이미지 담을 list
            LocalDateTime startDate = groupChallenge.getStartAt();                          // 챌린지 시작날짜 LocalDateTime -> Date
            Date startAt = java.sql.Timestamp.valueOf(startDate);
            List<GroupChallengeMember> groupChallengeMemberList = groupChallengeMemberRepository.findByGroupChallenge(groupChallenge); // 그룹 챌린지로 챌린지 참가자 객체받아오기
            int memberCount = 0;
            if (!groupChallengeMemberList.isEmpty()) {
                memberCount = groupChallengeMemberList.size();                          // 그룹 챌린지 참여자 수
                for (int j = 0; j < memberCount; j++) {
                    String profileImagePath = groupChallengeMemberList.get(j).getUser().getProfileImagePath();  // 챌린지 참여자 개인의 프로필 이미지
                    challengeMemberImagePathDTOList.add(profileImagePath);                  // list에 추가
                }
            }
            groupChallengeResponseDTOList.add(GroupChallengeResponseDTO.of(groupChallenge, startAt, memberCount, challengeMemberImagePathDTOList));
        }
        log.info("GroupChallengeService --- groupChallengeResponseDTO: {}", groupChallengeResponseDTOList);
        return groupChallengeResponseDTOList;
    }


    public GroupChallengeDetailResponseDTO getGroupChallengeDetail(
            UUID userId,
            Long groupId,
            Long groupChallengeId
    ) {
        User user = userRepository.findById(userId)                                         // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)                                     // 그룹 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));
        GroupMember groupMember = groupMemberRepository.findByUserAndGroupAndIsDeleted(user, group, false)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속한 유저가 아닙니다"));
        GroupChallenge groupChallenge = groupChallengeRepository.findByGroupChallengeIdAndState(groupChallengeId, "ACTIVATED") // 그룹 챌린지 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다"));
        GroupChallengeMember groupChallengeMember = groupChallengeMemberRepository.findByUserAndGroupChallenge(user, groupChallenge)
                .orElseThrow(() -> new IllegalArgumentException("챌린지에 속하지 않은 유저입니다"));
        List<GroupChallengeMember> groupChallengeMemberList = groupChallengeMemberRepository.findByGroupChallenge(groupChallenge);  // 해당 챌린지에 참여하는 멤버들 리스트 받아오기

        List<GroupChallengeMemberDTO> groupChallengeMemberDTOList = new ArrayList<>();                  // 반환 배열

        LocalDate today = LocalDate.now(ZoneOffset.UTC);
        for (int i = 0; i < groupChallengeMemberList.size(); i++) {                                     // 챌린지 멤버들을 순회
            UUID tempUserId = groupChallengeMemberList.get(i).getUser().getUserId();                   // 챌린지 멤버 userId 받아온다
            GroupChallengeLog groupChallengeLog = groupChallengeLogRepository.findByUserIdAndGroupChallengeIdAndTime(tempUserId, groupChallengeId, today);  // 유저Id, 챌린지Id, 오늘날짜로 로그를 찾아온다
            GroupChallengeMemberDTO groupChallengeMemberDTO = GroupChallengeMemberDTO.of(groupChallengeMemberList.get(i), groupChallengeLog != null); // 오늘 안찍은 사람이면 false, 찍은 사람은 true로 DTO에 저장된다
            groupChallengeMemberDTOList.add(groupChallengeMemberDTO);                                   // 각 개인의 정보를 리스트에 넣는다
        }
        return GroupChallengeDetailResponseDTO.of(groupChallenge, groupChallengeMemberDTOList);         // 그룹 챌린지의 이름 등 데이터와 챌린지 멤버들의 정보를 담아서 반환
    }

    @Transactional

    public boolean checkGroupStreak(
            UUID userId,
            Long groupChallengeId,
            String nickname
    ) {
        User user = userRepository.findById(userId)                                             // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        GroupChallenge groupChallenge = groupChallengeRepository.findByGroupChallengeIdAndState(groupChallengeId, "ACTIVATED")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다"));  // 그룹 챌린지 존재 유무 체크
        GroupChallengeMember groupChallengeMember = groupChallengeMemberRepository.findByUserAndGroupChallenge(user, groupChallenge)
                .orElseThrow(() -> new IllegalArgumentException("챌린지에 속한 유저가 아닙니다"));
        if (!user.getNickname().equals(nickname)) {                                         // 본인의 체크박스인지 체크
            throw new IllegalArgumentException("타인의 스트릭 체크 박스입니다");
        }

        LocalDate time = LocalDate.now(ZoneOffset.UTC);                                                   // 오늘 날짜
        GroupChallengeLog todayGroupChallengeLog = groupChallengeLogRepository.findByUserIdAndGroupChallengeIdAndTime(userId, groupChallengeId, time); // 오늘 날짜 스트릭 로그 조회
        if (todayGroupChallengeLog != null) {                                               // 스트릭 존재한다면
            throw new IllegalArgumentException("이미 금일 스트릭을 채우셨습니다");                // 예외처리
        }
        GroupChallengeLog groupChallengeLog = GroupChallengeLog.builder()                  // 유저의 금일 그룹 챌린지 스트릭 로그 저장
                .groupChallengeId(groupChallengeId)
                .userId(userId)
                .time(time)
                .build();
        return groupChallengeLogRepository.save(groupChallengeLog) != null;
    }

    @Transactional

    public boolean challengeApplication(
            UUID userId,
            Long groupChallengeId
    ) {
        User user = userRepository.findById(userId)                                               // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        GroupChallenge groupChallenge = groupChallengeRepository.findById(groupChallengeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지 입니다"));    // 그룹 챌린지 존재 유무 체크
        if (!groupChallenge.getState().equals("WAITING")) {
            throw new IllegalArgumentException("이미 시작했거나 종료된 챌린지는 참여신청을 할 수 없습니다"); // 예약 상태인 챌린지인지 체크
        }
        Group group = groupChallenge.getGroup();                                                // 그룹 객체 받아오기
        GroupMember groupMember = groupMemberRepository.findByUserAndGroupAndIsDeleted(user, group, false)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속한 유저가 아닙니다"));

        List<GroupChallengeMember> memberCount = groupChallengeMemberRepository.findByGroupChallenge(groupChallenge);
        if (memberCount.size() > groupChallenge.getMaxMemberCount()) {                          // 해당 챌린지 현재 참여 가능 인원수 체크
            throw new IllegalArgumentException("해당 챌린지는 최대 참여 가능 인원 수가 다 찼습니다");
        }
        GroupChallengeApplication isPresent = groupChallengeApplicationRepository.findByUserAndGroupChallengeAndState(user, groupChallenge, "WAITING");
        if (isPresent != null) {
            throw new IllegalArgumentException("이미 가입 신청한 챌린지 입니다");
        }

        Long challengeId = groupChallenge.getChallenge().getChallengeId();
        List<Long> temp = new ArrayList<>();                                                      // 하고 있는 챌린지 id 담을 배열
        List<GroupChallengeMember> groupChallengeMemberList = groupChallengeMemberRepository.findByUser(user); // 내가 참여중인 그룹 챌린지들

        if (!groupChallengeMemberList.isEmpty()) {
            for (int i = 0; i < groupChallengeMemberList.size(); i++) {

                GroupChallenge tempGroupChallenge = groupChallengeRepository.findById(groupChallengeMemberList.get(i).getGroupChallenge().getGroupChallengeId()) // 그룹챌린지를 찾아서
                        .orElseThrow(() -> new IllegalArgumentException("알수없는 에러"));
                if (tempGroupChallenge.getState().equals("ACTIVATED")) {                                 // 진행중인 챌린지라면
                    Long tempChallengeId = tempGroupChallenge.getChallenge().getChallengeId();          // 그것의 챌린지 아이디를 찾는다
                    temp.add(tempChallengeId);                                                      // 그룹에서 참여하고 있는 challenge들의 id값을 temp에 저장
                }
            }
        }
        List<MyChallenge> myChallengeList = myChallengeRepository.findByUser(user);                 // 개인 챌린지들을 찾아서
        if (!myChallengeList.isEmpty()) {
            for (int i = 0; i < myChallengeList.size(); i++) {
                String isActivated = myChallengeList.get(i).getState();
                if (isActivated.equals("ACTIVATED")) {
                    Long tempChallengeId = myChallengeList.get(i).getChallenge().getChallengeId();      // 그것들의 챌린지 아이디를 찾는다
                    temp.add(tempChallengeId);                                                          // 개인이 하고 있는 챌린지들의 id를 temp에 저장
                }
            }
        }

        if (temp.contains(challengeId)) {                                                               // 신청하려는 챌린지와 내가 진행중인 챌린지를 비교해서
            throw new IllegalArgumentException("이미 진행중인 챌린지는 참가 신청 할 수 없습니다");            // 이미 하고 있는 챌린지면 신청 불가
        }
        String state = "WAITING";                                                                       // 웨이팅 상태로 신청
        GroupChallengeApplication groupChallengeApplication = GroupChallengeApplication.builder()
                .groupChallenge(groupChallenge)
                .user(user)
                .state(state)
                .appliedAt(LocalDateTime.now())
                .build();
        return groupChallengeApplicationRepository.save(groupChallengeApplication) != null;
    }

    public List<ApplicationListResponseDTO> getChallengeApplicationList(
            UUID userId,
            Long groupChallengeId
    ) {
        User user = userRepository.findById(userId)                                               // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        GroupChallenge groupChallenge = groupChallengeRepository.findById(groupChallengeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹 챌린지입니다"));
        if (!groupChallenge.getState().equals("WAITING")) {
            throw new IllegalArgumentException("이미 시작했거나 종료된 챌린지입니다");                  // 예약 상태인 챌린지인지 체크
        }
        Group group = groupChallenge.getGroup();

        GroupMember groupMember = groupMemberRepository.findByUserAndGroupAndIsDeleted(user, group, false)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속한 유저가 아닙니다"));
        if (!groupMember.getAuthority().equals("OWNER") && !groupMember.getAuthority().equals("MANAGER")) {
            throw new IllegalArgumentException("챌린지 신청자 관리 권한이 없습니다");
        }
        List<ApplicationListResponseDTO> ApplicationListResponseDTOs= new ArrayList<>();          // 신청자 명단을 담을 배열
        List<GroupChallengeApplication> groupChallengeApplications = groupChallengeApplicationRepository.findByGroupChallengeAndState(groupChallenge, "WAITING");   // 신청정보에서 대기중인 사람만 가져온다
        if (groupChallengeApplications != null) {
            for (int i = 0; i < groupChallengeApplications.size(); i++) {                         // 각 신청에서 유저정보를 DTO로 변환해 담는다
                GroupChallengeApplication applicant = groupChallengeApplications.get(i);
                ApplicationListResponseDTO responseDTO = ApplicationListResponseDTO.of(applicant);
                ApplicationListResponseDTOs.add(responseDTO);
            }
        }
        return ApplicationListResponseDTOs;
    }
    @Transactional
    public boolean manageSubscriptionApplication(
            UUID userId,
            Long groupChallengeId,
            String nickname,
            String state
    ) {
        User user = userRepository.findById(userId)                                               // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        GroupChallenge groupChallenge = groupChallengeRepository.findById(groupChallengeId)         // 그룹 챌린지 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹 챌린지입니다"));
        Group group = groupChallenge.getGroup();                                                    // 그룹 객체 받아오기
        GroupMember isExecutive = groupMemberRepository.findByGroupAndUserAndIsDeleted(group, user, false)    // 삭제되지 않은 결정권자 객체를 받아오기
                .orElseThrow(() -> new IllegalArgumentException("당신은 그룹에 속하지 않았습니다"));
        if (!isExecutive.getAuthority().equals("OWNER") && !isExecutive.getAuthority().equals("MANAGER")) {      // 결정권자가 owner나 manager가 아니라면 권한 문제 발생
            throw new IllegalArgumentException("챌린지 참가 신청 관리에 대한 권한이 없습니다");
        }

        User appliedUser = userRepository.findByNickname(nickname)                                  // 가입 신청한 유저 객체
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        GroupChallengeApplication groupChallengeApplication = groupChallengeApplicationRepository.findByUserAndGroupChallenge(appliedUser, groupChallenge)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지 신청입니다"));
        if (state.equals("REJECTED")) {                                                             // 거절이라면 그룹 챌린지 신청 테이블에서 rejected로 변환
            // 그룹 챌린지 신청에서 상태값 REJECTED로 변환
            groupChallengeApplication.updateState(state);
            return true;

        }
        if (state.equals("ACCEPTED")) {
            List<GroupChallengeMember> memberCount = groupChallengeMemberRepository.findByGroupChallenge(groupChallenge);
            if (memberCount.size() > groupChallenge.getMaxMemberCount()) {                          // 챌린지 참여 가능인원 초과 여부 확인
                throw new IllegalArgumentException("해당 챌린지는 최대 참여 가능 인원 수가 다 찼습니다");
            }

            // 해당 유저가 동일 챌린지 참여중인지 확인

            List<Long> temp = new ArrayList<>();                                                      // 하고 있는 챌린지 id 담을 배열
            List<GroupChallengeMember> groupChallengeMemberList = groupChallengeMemberRepository.findByUser(appliedUser); // 유저가 참여중인 그룹 챌린지들

            if (!groupChallengeMemberList.isEmpty()) {
                for (int i = 0; i < groupChallengeMemberList.size(); i++) {

                    GroupChallenge tempGroupChallenge = groupChallengeRepository.findById(groupChallengeMemberList.get(i).getGroupChallenge().getGroupChallengeId()) // 그룹챌린지를 찾아서
                            .orElseThrow(() -> new IllegalArgumentException("알수없는 에러"));
                    if (tempGroupChallenge.getState().equals("ACTIVATED")) {                                 // 진행중인 챌린지라면
                        Long tempChallengeId = groupChallenge.getChallenge().getChallengeId();          // 그것의 챌린지 아이디를 찾는다
                        temp.add(tempChallengeId);                                                      // 그룹에서 참여하고 있는 challenge들의 id값을 temp에 저장
                    }
                }
            }

            List<MyChallenge> myChallengeList = myChallengeRepository.findByUser(appliedUser);                 // 개인 챌린지들을 찾아서
            if (!myChallengeList.isEmpty()) {
                for (int i = 0; i < myChallengeList.size(); i++) {
                    String isActivated = myChallengeList.get(i).getState();
                    if (isActivated.equals("ACTIVATED")) {
                        Long tempChallengeId = myChallengeList.get(i).getChallenge().getChallengeId();      // 그것들의 챌린지 아이디를 찾는다
                        temp.add(tempChallengeId);                                                          // 개인이 하고 있는 챌린지들의 id를 temp에 저장
                    }
                }
            }
            if (temp.contains(groupChallengeId)) {                                                          // 신청한 유저가 동일 챌린지에 참여중이면
                groupChallengeApplication.updateState("REJECTED");                                          // group_challenge_application의 state값 rejected로 update
                throw new IllegalArgumentException("해당 유저는 동일한 챌린지에 참여 중입니다");                  // 예외처리
            }

            groupChallengeApplication.updateState("ACCEPTED");                                              // 그룹 챌린지에서 상태값 ACCEPTED로 변환
            GroupChallengeMember groupChallengeMember = GroupChallengeMember.builder()                      // 그룹 챌린지 멤버에 해당 유저 추가
                    .user(appliedUser)
                    .groupChallenge(groupChallenge)
                    .build();
            return groupChallengeMemberRepository.save(groupChallengeMember) != null;
        } else {
            throw new IllegalArgumentException("올바른 상태값이 아닙니다");
        }
    }

    public long getCountOfSuccessfulGroupChllaenges(Group group) {
        return groupChallengeRepository.countByGroupAndStateIn(group, List.of("SUCCESSFUL"));
    }

    public long getCountOfActivatedGroupChllaenges(Group group) {
        return groupChallengeRepository.countByGroupAndStateIn(group, List.of("ACTIVATED"));
    }

    public List<GroupChallengeForGroupIntroPageResDTO> getGroupChallengeListByIdAndStates(Group group) {
        List<GroupChallenge> groupChallengeList = groupChallengeRepository.findByGroupAndStateIn(group, Arrays.asList("ACTIVATED", "WAITING"));
        List<GroupChallengeForGroupIntroPageResDTO> groupChallengeResponseDTOList = new ArrayList<>();

        for (GroupChallenge groupChallenge : groupChallengeList) {
            Long groupChallengeId = groupChallenge.getGroupChallengeId();
            Long challengeId = groupChallenge.getChallenge().getChallengeId();
            String challengeContent = groupChallenge.getContent();
            String challengeName = groupChallenge.getChallengeName();
            String challengeTopic = groupChallenge.getChallenge().getTopic();
            List<String> profileImagePathList =
                    groupChallengeMemberRepository
                            .findByGroupChallenge(groupChallenge)
                            .stream()
                            .map(groupChallengeMember -> groupChallengeMember.getUser().getProfileImagePath())
                            .collect(Collectors.toList());
            int maxMemberCount = groupChallenge.getMaxMemberCount();
            int memberCount = profileImagePathList.size();
            LocalDate startAt = groupChallenge.getStartAt().toLocalDate();
            LocalDate endAt = groupChallenge.getEndAt().toLocalDate();
            String dDay;

            LocalDate today = LocalDate.now();
            long daysDiff = ChronoUnit.DAYS.between(startAt, today);
            if (daysDiff == 0L) {
                dDay = "D-DAY";
            } else if (0 < daysDiff) {
                dDay = "D + " + daysDiff;
            } else {
                dDay = "D - " + daysDiff;
            }

            groupChallengeResponseDTOList.add(
                    GroupChallengeForGroupIntroPageResDTO.builder()
                            .groupChallengeId(groupChallengeId)
                            .challengeId(challengeId)
                            .challengeContent(challengeContent)
                            .challengeName(challengeName)
                            .challengeTopic(challengeTopic)
                            .profileImagePathList(profileImagePathList)
                            .maxMemberCount(maxMemberCount)
                            .memberCount(memberCount)
                            .startAt(startAt)
                            .endAt(endAt)
                            .dDay(dDay)
                            .build()
            );
        }
        return groupChallengeResponseDTOList;
    }
}
