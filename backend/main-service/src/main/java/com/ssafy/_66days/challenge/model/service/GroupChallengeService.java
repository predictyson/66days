package com.ssafy._66days.challenge.model.service;

import com.ssafy._66days.challenge.model.dto.GroupChallengeMemberDTO;
import com.ssafy._66days.challenge.model.dto.requestDTO.GroupChallengeRequestDTO;
import com.ssafy._66days.challenge.model.dto.responseDTO.AvailableGroupChallengeResponseDTO;
import com.ssafy._66days.challenge.model.dto.responseDTO.GroupChallengeDetailResponseDTO;
import com.ssafy._66days.challenge.model.dto.responseDTO.GroupChallengeResponseDTO;
import com.ssafy._66days.challenge.model.entity.Challenge;
import com.ssafy._66days.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.challenge.model.entity.GroupChallengeMember;
import com.ssafy._66days.challenge.model.reposiotry.ChallengeRepository;
import com.ssafy._66days.challenge.model.reposiotry.GroupChallengeMemberRepository;
import com.ssafy._66days.challenge.model.reposiotry.GroupChallengeRepository;
import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.group.model.entity.GroupMember;
import com.ssafy._66days.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.group.model.repository.GroupRepository;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service("GroupChallengeService")
public class GroupChallengeService {
    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupRepository groupRepository;
    private final ChallengeRepository challengeRepository;
    private final GroupChallengeRepository groupChallengeRepository;
    private final GroupChallengeMemberRepository groupChallengeMemberRepository;

    public GroupChallengeService(
            UserRepository userRepository,
            GroupMemberRepository groupMemberRepository,
            GroupRepository groupRepository,
            ChallengeRepository challengeRepository,
            GroupChallengeRepository groupChallengeRepository,
            GroupChallengeMemberRepository groupChallengeMemberRepository
    ) {
        this.userRepository = userRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.groupRepository = groupRepository;
        this.challengeRepository = challengeRepository;
        this.groupChallengeRepository = groupChallengeRepository;
        this.groupChallengeMemberRepository = groupChallengeMemberRepository;
    }

    //
    public boolean createGroupChallenge(
            UUID userId,
            Long groupId,
            GroupChallengeRequestDTO groupChallengeRequestDTO
    ) {
        User user = userRepository.findById(userId)                         // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹니다"));
        GroupMember groupMember = groupMemberRepository.findByGroupAndUser(group, user)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속하지 않은 유저입니다"));
        Challenge challenge = challengeRepository.findById(groupChallengeRequestDTO.getChallengeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지 입니다"));

        if (groupMember.getAuthority() != "OWNER" && groupMember.getAuthority() != "MANAGER") {
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

        Date endDate = groupChallengeRequestDTO.getStartAt();
        LocalDateTime endAt = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());  // 입력받은 종료날짜를 LocalDateTime으로 변환

        LocalDateTime today = LocalDateTime.now();          // 오늘 날짜

        String state = "ACTIVATE";
        GroupChallenge groupChallenge = groupChallengeRepository.findByGroupAndChallengeAndState(group, challenge, state); // 현재 진행 중인 챌린지가 있는지 찾아온다

        if (startAt.compareTo(today) > 30) {                //  시작날짜가 오늘 날짜에서 30일 초과한 날짜인지 확인
            throw new IllegalArgumentException("챌린지는 최대 30일 이내에 시작해야 합니다");
        }
        if (groupChallenge != null && groupChallenge.getEndAt().isAfter(startAt)) { // 같은 챌린지가 해당 그룹내에서 진행중인지 확인(시작날짜가 진행 중 챌린지 endAt보다 전인지 확인)
            throw new IllegalArgumentException("동일한 챌린지가 그룹 내에서 진행 중인 날짜에는 챌린지를 시작할 수 없습니다");
        }


        String status = "ACTIVATE";                         // 새로 시작할 챌린지 상태값
        int availableFreezingCount = 0;                     // 사용가능 프리징 수 기본값
        GroupChallenge newGroupChallenge = GroupChallenge.builder()
                .group(group)
                .challenge(challenge)
                .content(groupChallengeRequestDTO.getContent())
                .startAt(startAt)
                .endAt(endAt)
                .state(status)
                .availableFreezingCount(availableFreezingCount)
                .maxMemberCount(groupChallengeRequestDTO.getMaxMemberCount())
                .build();
        return groupChallengeRepository.save(newGroupChallenge) != null;
    }

    public List<AvailableGroupChallengeResponseDTO>  getAvailableGroupChallengeList(
            UUID userId,
            Long groupId
    ) {
        User user = userRepository.findById(userId)                         // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)                     // 그룹 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹니다"));
        GroupMember groupMember = groupMemberRepository.findByGroupAndUser(group, user)     // 그룹멤버 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속한 유저가 아닙니다"));
        if (groupMember.getAuthority() != "OWNER" && groupMember.getAuthority() != "MANAGER") { // 그룹장이나 매니저가 아니라면 챌린지를 만들 수 없다
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
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹니다"));
        GroupMember groupMember = groupMemberRepository.findByGroupAndUser(group, user)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속한 유저가 아닙니다"));

        // 그룹아이디로 진행중인 챌린지와 진행 예정인 챌린지 리스트를 받아온다
        List<GroupChallenge> groupChallengeList = groupChallengeRepository.findByGroupAndStateIn(group, Arrays.asList("ACTIVATED", "WAITING"));
        List<GroupChallengeResponseDTO> groupChallengeResponseDTOList = new ArrayList<>();  // 그룹 챌린지 반환 리스트
        List<String> challengeMemberImagePathDTOList = new ArrayList<>();                   // 참여자들의 프로필 이미지 담을 list
        for (int i = 0; i < groupChallengeList.size(); ) {
            GroupChallenge groupChallenge = groupChallengeList.get(i);                      // 각 그룹 챌린지 객체

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
        return groupChallengeResponseDTOList;
    }

    public GroupChallengeDetailResponseDTO getGroupChallengeDetail(
            UUID userId,
            Long groupId,
            Long groupChallengeId
    ) {
        User user = userRepository.findById(userId)                         // 유저 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)                     // 그룹 객체 받아오기
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹니다"));
        GroupChallenge groupChallenge = groupChallengeRepository.findById(groupChallengeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다"));
        List<GroupChallengeMember> groupChallengeMemberList = groupChallengeMemberRepository.findByGroupChallenge(groupChallenge);

        List<GroupChallengeMemberDTO> groupChallengeMemberDTOList = new ArrayList<>();
        if (!groupChallengeMemberList.isEmpty()) {
            for (int i = 0; i < groupChallengeMemberList.size(); i++) {
                GroupChallengeMemberDTO groupChallengeMemberDTO = GroupChallengeMemberDTO.of(groupChallengeMemberList.get(i));
                groupChallengeMemberDTOList.add(groupChallengeMemberDTO);
            }
        }
        GroupChallengeDetailResponseDTO groupChallengeDetailResponseDTO = GroupChallengeDetailResponseDTO.of(groupChallenge, groupChallengeMemberDTOList);
        return groupChallengeDetailResponseDTO;
    }
}
