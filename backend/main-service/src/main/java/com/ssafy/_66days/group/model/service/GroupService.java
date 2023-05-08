package com.ssafy._66days.group.model.service;

import com.ssafy._66days.group.model.dto.GroupSearchPageResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService {


    public List<GroupSearchPageResponseDTO> searchGroup(String searchContent, String filterBy) {


        return null;
    }

    public List<MemberManageDTO> getGroupMembers(int groupId) {
    }
}
