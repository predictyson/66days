package com.ssafy._66days.mono.item.model.dto.ResponseDTO;

import com.ssafy._66days.mono.item.model.entity.Inventory;
import com.ssafy._66days.mono.item.model.entity.Item;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InventoryResponseDTO {
    private int quantity;

    private String imagePath;
    public static InventoryResponseDTO of(Inventory inventory, Item item) {
        return InventoryResponseDTO.builder()
                .quantity(inventory.getQuantity())
                .imagePath(item.getImagePath())
                .build();
    }
}
