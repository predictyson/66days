package com.ssafy.mock66days.rank.model.dto;

import com.ssafy.mock66days.member.model.dto.MemberDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RankMainPageResponseDTO {
    private List<MemberDTO> expRank;
    private List<MemberDTO> badgeRank;
    private int myExpRank;
    private int myExp;
    private int myBadgeRank;
    private int myBadge;

}
