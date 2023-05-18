package com.ssafy._66days.mono.challenge.model.dto.requestDTO;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StreakRequestDTO {
    private Date today;
}
