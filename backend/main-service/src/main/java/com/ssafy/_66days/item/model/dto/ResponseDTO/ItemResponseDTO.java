package com.ssafy._66days.item.model.dto.ResponseDTO;

import com.ssafy._66days.item.model.entity.Item;
import com.ssafy._66days.user.model.entity.User;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemResponseDTO {
    private Long itemId;
    private String itemName;
    private int price;
    private String imagePath;
    private Long point;

    public static ItemResponseDTO of(User user, Item item) {
        return ItemResponseDTO.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .imagePath(item.getImagePath())
                .point(user.getPoint())
                .build();
    }


}
