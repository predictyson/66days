package com.ssafy.mock66days.rank.model.dto;

import com.ssafy.mock66days.member.model.dto.Member;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RankMainPageResponseDTO {
    private List<Member> expRank;
    private List<Member> badgeRank;
    private int myExpRank;
    private int myExp;
    private int myBadgeRank;
    private int myBadge;

}
