package com.ssafy._66days.mainservice.group.model.service;

import com.ssafy._66days.mainservice.global.util.FileUtil;
import com.ssafy._66days.mainservice.group.model.dto.GroupCreateDTO;
import com.ssafy._66days.mainservice.group.model.dto.GroupSearchPageResponseDTO;
import com.ssafy._66days.mainservice.group.model.entity.Group;
import com.ssafy._66days.mainservice.group.model.entity.GroupApply;
import com.ssafy._66days.mainservice.group.model.entity.GroupMember;
import com.ssafy._66days.mainservice.group.model.repository.GroupApplyRepository;
import com.ssafy._66days.mainservice.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.mainservice.group.model.repository.GroupRepository;
import com.ssafy._66days.mainservice.user.model.dto.UserManageDTO;
import com.ssafy._66days.mainservice.user.model.entity.User;
import com.ssafy._66days.mainservice.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public List<GroupSearchPageResponseDTO> searchGroup(String searchContent, String filterBy) {
        User user = userRepository.findByNickname(searchContent).orElse(null);
        List<Group> groups = null;
        if(user == null) {
           groups  = groupRepository.findAllByGroupNameContains(searchContent);
        } else {
            groups = groupRepository.findAllByGroupNameContainsOrOwnerId(searchContent, user.getUserId());
        }
        // TODO: categories 리스트 추후, 챌린지 구현 후 추가
        // TODO: categories 릿스트는 filterBy로 찾기
        List<GroupSearchPageResponseDTO> groupDTOList = new ArrayList<>();
        for (Group group:groups) {
            GroupSearchPageResponseDTO pageResponseDTO = GroupSearchPageResponseDTO.of(group, user);
            Long memberCount = groupMemberRepository.countByGroupAndIsDeleted(group, false);
            pageResponseDTO.setMemberCounts(memberCount);


            groupDTOList.add(pageResponseDTO);
        }

        return groupDTOList;
    }

    public List<UserManageDTO> getGroupMembers(Long groupId) {
        List<GroupMember> groupMemberList = groupMemberRepository.findByGroup(groupRepository.findById(groupId));
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

    public void createGroup(GroupCreateDTO groupCreateDTO, MultipartFile image) throws IOException {
        if (groupCreateDTO == null || image.isEmpty()) {
            throw new InputMismatchException("필요한 값이 들어오지 않았습니다.");
        }
        String savePath = fileUtil.fileUpload(image, groupImageFilePath);

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
}
