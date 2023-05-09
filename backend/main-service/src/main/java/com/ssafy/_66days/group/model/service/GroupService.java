package com.ssafy._66days.group.model.service;

import com.ssafy._66days.global.config.S3Upload;
import com.ssafy._66days.group.model.dto.GroupCreateDTO;
import com.ssafy._66days.group.model.dto.GroupSearchPageResponseDTO;
import com.ssafy._66days.group.model.entity.GroupMember;
import com.ssafy._66days.group.model.repository.GroupApplyRepository;
import com.ssafy._66days.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.group.model.repository.GroupRepository;
import com.ssafy._66days.user.model.dto.UserManageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupApplyRepository groupApplyRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final S3Upload s3Upload;

    @Autowired
    public GroupService(GroupRepository groupRepository, GroupApplyRepository groupApplyRepository,
                        GroupMemberRepository groupMemberRepository, S3Upload s3Upload) {
        this.groupRepository = groupRepository;
        this.groupApplyRepository = groupApplyRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.s3Upload = s3Upload;
    }


    public List<GroupSearchPageResponseDTO> searchGroup(String searchContent, String filterBy) {


        return null;
    }

    public List<UserManageDTO> getGroupMembers(int groupId) {

        return null;
    }

    public List<UserManageDTO> getGroupApplyList(int groupId) {
        return null;
    }

    public void setGroupMemberState(int groupId, String state, String userName) {
    }

    public void setGroupApplyState(int groupId, String state, String userName) {
    }

    public void applyGroup(int groupId, String state, UUID userId) {
    }

    public void createGroup(GroupCreateDTO groupCreateDTO, MultipartFile image) {
        String url = uploadSentenceImages(multipartFile);
        Sentence sentence = sentenceRepository.findBySentenceId(sentenceId);
        sentence.setSentenceImageUrl(url);
        sentenceRepository.save(sentence);
        return url;
        groupRepository.save(groupCreateDTO);

    }
}
