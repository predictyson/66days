package com.ssafy._66days.group.model.service;

import com.ssafy._66days.global.config.S3Upload;
import com.ssafy._66days.group.model.dto.GroupCreateDTO;
import com.ssafy._66days.group.model.dto.GroupSearchPageResponseDTO;
import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.group.model.entity.GroupApply;
import com.ssafy._66days.group.model.entity.GroupMember;
import com.ssafy._66days.group.model.repository.GroupApplyRepository;
import com.ssafy._66days.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.group.model.repository.GroupRepository;
import com.ssafy._66days.user.model.dto.UserManageDTO;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupApplyRepository groupApplyRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final S3Upload s3Upload;

    @Autowired
    public GroupService(UserRepository userRepository,
                        GroupRepository groupRepository, GroupApplyRepository groupApplyRepository,
                        GroupMemberRepository groupMemberRepository, S3Upload s3Upload) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.groupApplyRepository = groupApplyRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.s3Upload = s3Upload;
    }

    public List<GroupSearchPageResponseDTO> searchGroup(String searchContent, String filterBy) {
        List<Group> groups = groupRepository.findAllByGroupNameContains(searchContent);
        // TODO: 카테고리 모집중 이해가 잘 안되고 그룹장, 그룹명 같이 찾는거 좀 더 논의 해봐야함
        return null;
    }

    public List<UserManageDTO> getGroupMembers(Long groupId) {
        List<GroupMember> groupMemberList = groupMemberRepository.findByGroup(groupRepository.findById(groupId));
        List<UserManageDTO> userManageDTOList = groupMemberList.stream()
                .map(groupMember -> UserManageDTO.of(userRepository.findById(groupMember.getUser().getUserId()).orElseThrow(() -> new NoSuchElementException("user doesn't exist"))
                        ,groupMember)).collect(Collectors.toList());

        // 유저 권한 체크 하지 않고 그룹에 속한 사람 모두 반환 중, 23-05-10 기준
        // TODO: 필요시, 자신보다 권한이 높은 사람 반환 안하는 부분 필요하면 추가해야함
        log.info("userManageDtoList: {}", userManageDTOList);

        return userManageDTOList;
    }

    public List<UserManageDTO> getGroupApplyList(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("group doesn't exist"));
        List<GroupApply> applyList = groupApplyRepository.findAllByStateAndGroup("WAITING", group);

        // 대기열 중에 'WAITING'인 상태인 유저를 'authority=NULL' 채워서 반환
        List<UserManageDTO> userManageDTOList = applyList.stream().map(apply ->
            UserManageDTO.of(userRepository.findById(apply.getUser().getUserId()).orElseThrow(() -> new NoSuchElementException("user doesn't exist")))
                ).collect(Collectors.toList());

        return userManageDTOList;
    }

    public void setGroupMemberState(Long groupId, String state, String userName) throws InputMismatchException {
        state = state.toUpperCase();
        if (!(state.equals("MANAGER") || state.equals("MEMBER"))) throw new InputMismatchException("권한 설정이 잘못 입력되었습니다");
        User user = userRepository.findByNickname(userName).orElseThrow(() -> new NoSuchElementException("user doesn't exist"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("group doesn't exist"));
        GroupMember groupMember = groupMemberRepository.findByGroupAndUser(group,user).orElseThrow(() -> new NoSuchElementException("user is not in a group"));
        log.info("group member state BEFORE: {}", groupMember.getAuthority());
        groupMember.setAuthority(state);

//        groupMember = groupMemberRepository.findByGroupAndUser(group,user).orElseThrow(() -> new NoSuchElementException("user is not in a group"));
        log.info("group member state AFTER: {}", groupMember.getAuthority());

    }

    public void setGroupApplyState(Long groupId, String state, String userName) {
    }

    public void applyGroup(Long groupId, String state, UUID userId) {
    }

    public void createGroup(GroupCreateDTO groupCreateDTO, MultipartFile image) {
        groupRepository.save(groupCreateDTO.toEntity(groupCreateDTO));

    }
}
