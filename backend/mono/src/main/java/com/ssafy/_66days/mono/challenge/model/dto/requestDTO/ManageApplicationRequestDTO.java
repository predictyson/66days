package com.ssafy._66days.mono.challenge.model.dto.requestDTO;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ManageApplicationRequestDTO {
    private String nickName;
    private String state;
}
