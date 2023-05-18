package com.ssafy._66days.mono.item.model.entity;


import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryId implements Serializable { // 인벤터리 복합키 설정
    // Entity에 객체로 되어 있지만 결국 id값이 필요한 것이므로 타입을 id들의 type에 맞게 지정
    // Entity의 필드 값과 맞춰주기 위해 user, item으로 써준다
    private UUID user;
    private Long item;
}
