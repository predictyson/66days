package com.ssafy._66days.mainservice.page.model.dto;

import com.ssafy._66days.mainservice.user.model.dto.UserDetailDTO;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MainPageResponseDTO {
    private UserDetailDTO userDetail;
    private List<Object> todayTodo;
    private MainPageMyGroupResponseDTO myGroup;
    private List<MainPageGroupResponseDTO> Group;


    public static MainPageResponseDTO of(
            UserDetailDTO userDetail,
            List<Object> todayTodo,
            MainPageMyGroupResponseDTO myGroup,
            List<MainPageGroupResponseDTO> Group
    ) {
        return MainPageResponseDTO.builder()
                .userDetail(userDetail)
                .todayTodo(todayTodo)
                .myGroup(myGroup)
                .Group(Group)
                .build();
    }
}
