package com.ssafy._66days.mono.group.model.service;

import com.ssafy._66days.mono.challenge.model.dto.ChallengeMyPageResponseDTO;
import com.ssafy._66days.mono.challenge.model.entity.Challenge;
import com.ssafy._66days.mono.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mono.challenge.model.reposiotry.ChallengeRepository;
import com.ssafy._66days.mono.challenge.model.reposiotry.GroupChallengeRepository;
import com.ssafy._66days.mono.global.util.FileUtil;
import com.ssafy._66days.mono.group.model.dto.*;
import com.ssafy._66days.mono.group.model.entity.Group;
import com.ssafy._66days.mono.group.model.entity.GroupAchievement;
import com.ssafy._66days.mono.group.model.entity.GroupApply;
import com.ssafy._66days.mono.group.model.entity.GroupMember;
import com.ssafy._66days.mono.group.model.repository.GroupAchievementRepository;
import com.ssafy._66days.mono.group.model.repository.GroupApplyRepository;
import com.ssafy._66days.mono.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.mono.group.model.repository.GroupRepository;
import com.ssafy._66days.mono.user.model.dto.UserManageDTO;
import com.ssafy._66days.mono.user.model.entity.User;
import com.ssafy._66days.mono.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupApplyRepository groupApplyRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupAchievementRepository groupAchievementRepository;
    private final GroupChallengeRepository groupChallengeRepository;
    private final ChallengeRepository challengeRepository;
    private final FileUtil fileUtil;

    @Value("${file.path.upload-images-groups}")
    private String groupImageFilePath;

    private final String APPLIED = "APPLIED";
    private final String CANCELLED = "CANCELLED";
    private final String ACCEPTED = "ACCEPTED";
    private final String REJECTED = "REJECTED";
    private final String WAITING = "WAITING";
    private final String MANAGER = "MANAGER";
    private final String MEMBER = "MEMBER";
    private final String DROP = "DROP";

    public List<GroupSearchPageResponseDTO> searchGroup(String searchContent, int pgNo) {
        User user = userRepository.findByNickname(searchContent).orElse(null);
        Page<Group> groups = null;
        PageRequest pageRequest = PageRequest.of(pgNo, 9);
        if(user == null) {
           groups  = groupRepository.findAllByGroupNameContains(searchContent, pageRequest);
           log.info("Group Service - searchGroup only name: {}", groups.getContent());
        } else {
            groups = groupRepository.findAllByGroupNameContainsOrOwnerId(searchContent, user.getUserId(),pageRequest);
            log.info("Group Service - searchGroup name and user: {}", groups.getContent());
        }
        List<Group> groupList = groups.getContent();
        // TODO: categories 리스트 추후, 챌린지 구현 후 추가
        List<GroupSearchPageResponseDTO> groupDTOList = new ArrayList<>();
        for (Group group:groupList) {
            User owner = userRepository.findById(group.getOwnerId()).orElseThrow(()->new NoSuchElementException("존재하지 않는 유저입니다"));
            GroupSearchPageResponseDTO pageResponseDTO = GroupSearchPageResponseDTO.of(group, owner);
            Long memberCount = groupMemberRepository.countByGroupAndIsDeleted(group, false);
            pageResponseDTO.setMemberCounts(memberCount);

            groupDTOList.add(pageResponseDTO);
        }

        return groupDTOList;
    }

    public List<UserManageDTO> getGroupMembers(Long groupId) {
        List<GroupMember> groupMemberList = groupMemberRepository.findByGroupAndAuthorityNot(groupRepository.findById(groupId),DROP);
        List<UserManageDTO> userManageDTOList = groupMemberList.stream()
                .map(groupMember -> UserManageDTO.of(userRepository.findById(groupMember.getUser().getUserId()).orElseThrow(() -> new NoSuchElementException("user doesn't exist"))
                        ,groupMember)).collect(Collectors.toList());

        log.info("userManageDtoList: {}", userManageDTOList);

        return userManageDTOList;
    }

    public List<UserManageDTO> getGroupApplyList(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("group doesn't exist"));
        List<GroupApply> applyList = groupApplyRepository.findAllByStateAndGroup(WAITING, group);

        // 대기열 중에 'WAITING'인 상태인 유저를 'authority=NULL' 채워서 반환
        List<UserManageDTO> userManageDTOList = applyList.stream().map(apply ->
            UserManageDTO.of(userRepository.findById(apply.getUser().getUserId()).orElseThrow(() -> new NoSuchElementException("user doesn't exist")))
                ).collect(Collectors.toList());

        return userManageDTOList;
    }

    public void setGroupMemberState(Long groupId, String state, String userName) throws InputMismatchException {
        state = state.toUpperCase();
        if (!(state.equals(MANAGER) || state.equals(MEMBER) || state.equals(DROP))) throw new InputMismatchException("권한 설정이 잘못 입력되었습니다");
        User user = userRepository.findByNickname(userName).orElseThrow(() -> new NoSuchElementException("user doesn't exist"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("group doesn't exist"));
        GroupMember groupMember = groupMemberRepository.findByGroupAndUser(group,user).orElseThrow(() -> new NoSuchElementException("user is not in a group"));
        if(state.equals(MANAGER)){
            // 매니저 인원 수: 최대 3명
            Long managerSize = groupMemberRepository.countByGroupAndAuthorityAndIsDeleted(group, MANAGER, false);
            if(managerSize >= 3L) throw new InputMismatchException("설정 가능한 매니저 수가 초과했습니다");
        } else if(state.equals(DROP)) {
            groupMember.updateIsDeleted(true);
        }
        log.info("group member state BEFORE: {}", groupMember.getAuthority());
        groupMember.updateAuthority(state);

        log.info("group member state AFTER: {}", groupMember.getAuthority());
    }

    public void setGroupApplyState(Long groupId, String state, String userName) throws Exception{
        state = state.toUpperCase();
        if (!(state.equals(ACCEPTED) || state.equals(REJECTED))) throw new InputMismatchException("그룹 가입승인 설정이 잘못 입력되었습니다");
        User user = userRepository.findByNickname(userName).orElseThrow(() -> new NoSuchElementException("user doesn't exist"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("group doesn't exist"));
        GroupApply groupApply = groupApplyRepository.findByUserAndGroup(user, group).orElseThrow(() -> new NoSuchElementException("groupApply doesn't exist"));
        if(state.equals(ACCEPTED)){
            // 가입한 그룹 수가 5개 이하인지 확인
            Long groupSize = groupMemberRepository.countByUserAndIsDeleted(user, false);
            if(groupSize > 5L) throw new InputMismatchException("사용자의 가입한 그룹 수가 한도 초과했습니다");
        }
        log.info("group apply state BEFORE: {}", groupApply.getState());
//        groupApply.setState(state);
        groupApply.updateGroupApply(state);
        if(state.equals(ACCEPTED)) {
            GroupMember groupMember = groupMemberRepository.findByUserAndGroupAndIsDeleted(user, group,true).orElse(null);
            log.info("group apply add groupmember: {}", groupMember);
            if (groupMember != null) {
                groupMember.updateIsDeleted(false);
                groupMember.updateAuthority(MEMBER);
            } else {
                groupMember = GroupMember.builder()
                                .user(user)
                                .group(group)
                                .authority(MEMBER)
                                .createdAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                                .build();
                log.info("group apply new groupmember: {}", groupMember);
                groupMemberRepository.save(groupMember);
            }
        }
        log.info("group apply state AFTER: {}", groupApply.getState());
    }

    /**
     * @param groupId
     * @param state
     * @param userId
     * 가입 신청은 어떤 상황에도 가능, 승인 여부를 확인할 때 조건확인
     */
    public void applyGroup(Long groupId, String state, UUID userId) {
        state = state.toUpperCase();
        if (!(state.equals(APPLIED) || state.equals(CANCELLED))) throw new InputMismatchException("그룹 가입신청이 잘못 입력되었습니다");
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("user doesn't exist"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("group doesn't exist"));
        GroupApply groupApply = null;

        if(state.equals(APPLIED)){
            state = WAITING;
            if(groupApplyRepository.findByUserAndGroupAndState(user,group,WAITING).isPresent()){
                return;
            }
            groupApply = new GroupApply(user, group, state);
        } else {
            groupApply = groupApplyRepository.findByUserAndGroupAndState(user,group,WAITING).orElse(null);
            groupApply.updateGroupApply(CANCELLED);
        }

        groupApplyRepository.save(groupApply);
    }

    public void createGroup(UUID userId, GroupCreateDTO groupCreateDTO, MultipartFile image) throws Exception {
        if (groupCreateDTO == null || image.isEmpty()) {
            throw new InputMismatchException("필요한 값이 들어오지 않았습니다.");
        }
        String savePath = fileUtil.fileUpload(image, groupImageFilePath);
        groupCreateDTO.setImage(savePath);
        log.info("GroupService -- groupCreateDTO: {}", groupCreateDTO);

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));

        if(groupRepository.findByOwnerId(userId).isPresent()){
            throw new InputMismatchException("사용자가 이미 소유한 그룹이 있습니다.");
        }

        Long groupSize = groupMemberRepository.countByUserAndIsDeleted(user, false);
        if(groupSize > 5L) throw new InputMismatchException("사용자의 가입한 그룹 수가 한도 초과했습니다");

        groupRepository.save(groupCreateDTO.toEntity(groupCreateDTO));
    }

    public boolean isUserGroupOwner(UUID userId, Long groupId) {
        boolean isOwner = groupRepository.findByOwnerIdAndGroupId(userId,groupId).isPresent();
        return isOwner;
    }

    public String getGroupName(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(()->new NoSuchElementException("해당하는 그룹이 없습니다"));
        return group.getGroupName();
    }

    public List<GroupAchievementResponseDTO> getGroupAchievement(UUID userId, Long groupId) {
        User user = userRepository.findById(userId)                                         // 유저 객체 받아오기
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));
        Group group = groupRepository.findById(groupId)                                     // 그룹 객체 받아오기
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 그룹입니다"));
        GroupMember groupMember = groupMemberRepository.findByGroupAndUserAndIsDeleted(group, user, false)  // 그룹멤버 객체 받아오기
                .orElseThrow(() -> new NoSuchElementException("그룹에 속하지 않는 유저입니다"));

        List<Long> temp = new ArrayList<>();                                                                    // 그룹에 업적이 있는지 없는지 구별할 빈 배열
        List<Challenge> challenges = challengeRepository.findAll();                                             // 챌린지 메타정보 객체 받아오기
        for (int i = 0; i < challenges.size(); i++) {                                                           // 챌린지 id들을 temp에 담는다
            temp.add(challenges.get(i).getChallengeId());
        }

        List<GroupAchievement> groupAchievements = groupAchievementRepository.findByGroup(group);               // 그룹의 업적 객체 받아오기
        List<GroupAchievementResponseDTO> GroupAchievementResponseDTOs = new ArrayList<>();                     // 업적 정보 저장할 빈 배열
        if (groupAchievements != null) {                                                                        // 그룹에 업적이 있다면
            for (int i = 0; i < groupAchievements.size(); i++) {                                                // 업적 정보를 DTO로 변환하여
                GroupAchievement tempGroupAchievement = groupAchievements.get(i);
                GroupAchievementResponseDTO groupAchievementResponseDTO = GroupAchievementResponseDTO.of(tempGroupAchievement);
                GroupAchievementResponseDTOs.add(groupAchievementResponseDTO);                                  // 배열에 담는다
                temp.remove(tempGroupAchievement.getChallenge().getChallengeId());                              // 나온 업적들은 temp에서 하나씩 지운다
            }
        }
        for (int j = 0; j < temp.size(); j++) {                                                                 // temp에 남은 challengeId = 그룹에게 하나도 없는 업적
            Long challengeId = temp.get(j);
            Challenge challenge = challengeRepository.findById(challengeId)                                     // 챌린지 객체를 가져와서
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다"));
            GroupAchievementResponseDTO groupAchievementResponseDTO = GroupAchievementResponseDTO.from(challenge);  // 객체로 만든다 이때 업적 갯수 0으로
            GroupAchievementResponseDTOs.add(groupAchievementResponseDTO);                                      // 반환 list에 추가한다

        }
        return GroupAchievementResponseDTOs;
    }

    public List<GroupAchievementDetailResponseDTO> getGroupAchievementDetail(Long groupId, Long challengeId) {
        Group group = groupRepository.findById(groupId)                                     // 그룹 객체 받아오기
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 그룹입니다"));
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 챌린지입니다"));
        List<GroupAchievementDetailResponseDTO> GroupAchievementDetailResponseDTOs = new ArrayList<>();
        List<String> stateList = new ArrayList<>();
        stateList.add("SUCCESSFUL");
        stateList.add("FAILED");
        for (String state : stateList) {
            List<GroupChallenge> groupChallenges = groupChallengeRepository.findByGroupAndChallengeAndState(group, challenge, state);
            if (groupChallenges != null) {
                for (int i = 0; i < groupChallenges.size(); i++) {
                    GroupChallenge groupChallenge = groupChallenges.get(i);
                    GroupAchievementDetailResponseDTO achievementDetailDTO = GroupAchievementDetailResponseDTO.of(groupChallenge);
                    GroupAchievementDetailResponseDTOs.add(achievementDetailDTO);
                }
            }
        }
        return GroupAchievementDetailResponseDTOs;
    }

    public List<GroupMyPageResponseDTO> findAllGroups(UUID userId) {
        User user = userRepository.findById(userId)                                         // 유저 객체 받아오기
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));
        List<GroupMember> groupMembers = groupMemberRepository.findAllByUser(user);
        List<GroupMyPageResponseDTO> groupMyPageResponseDTOList = new ArrayList<>();
        List<Challenge> challenges = null;
        for (int i = 0; i < groupMembers.size(); i++) {
            Group group = groupMembers.get(i).getGroup();
            List<GroupChallenge> groupChallenges = groupChallengeRepository.findByGroupAndState(group, "ACTIVATED");
            log.info("group challenge get challenge : {}", groupChallenges.get(i).getChallenge().getChallengeId());
            challenges = new ArrayList<>();
            for (int j = 0; j < groupChallenges.size(); j++) {
                challenges.add(groupChallenges.get(i).getChallenge());
            }
            List<ChallengeMyPageResponseDTO> challengeMyPageResponseDTOS = challenges.stream().map(c -> ChallengeMyPageResponseDTO.of(c)).collect(Collectors.toList());
            groupMyPageResponseDTOList.add(GroupMyPageResponseDTO.of(group, challengeMyPageResponseDTOS));
        }

        return groupMyPageResponseDTOList;
    }

    public Group findGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 그룹입니다."));
    }

    public String getUserAuthorityInGroup(Group group, User user) {
        Optional<GroupMember> groupMember = groupMemberRepository.findByGroupAndUser(group, user);
        if (groupMember.isEmpty()) {
            return "NOTMEMBER";
        } else if (groupMember.get().getAuthority().equals("MEMBER")) {
            return "MEMBER";
        } else if (groupMember.get().getAuthority().equals("OWNER")) {
            return "OWNER";
        } else {
            return "MANAGER";
        }
    }
}
