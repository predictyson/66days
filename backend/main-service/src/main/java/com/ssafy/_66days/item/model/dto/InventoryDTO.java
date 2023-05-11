package com.ssafy._66days.item.model.dto;

import com.ssafy._66days.item.model.entity.Item;
import com.ssafy._66days.user.model.entity.User;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InventoryDTO {
    private UUID userId;
    private Long itemId;

    private int quantity;

    public static InventoryDTO of(User user, Item item) {
        return InventoryDTO.builder()
                .userId(user.getUserId())
                .itemId(item.getItemId())
                .build();
    }
}
