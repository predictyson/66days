package com.ssafy._66days.mono.tier.model.dto;

import com.ssafy._66days.mono.tier.model.entity.Tier;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TierDTO {
    private Long tierId;
    private String name;
    private String imagePath;
    private String title;
    private Long requiredExp;

    public static TierDTO of(Tier tier){
        return TierDTO.builder()
                .tierId(tier.getTierId())
                .name(tier.getName())
                .imagePath(tier.getImagePath())
                .title(tier.getTitle())
                .requiredExp(tier.getRequiredExp())
                .build();
    }
}
