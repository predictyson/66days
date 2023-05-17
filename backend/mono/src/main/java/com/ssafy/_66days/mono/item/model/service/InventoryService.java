package com.ssafy._66days.mono.item.model.service;

import com.ssafy._66days.mono.item.model.dto.ResponseDTO.InventoryResponseDTO;
import com.ssafy._66days.mono.item.model.entity.Inventory;
import com.ssafy._66days.mono.item.model.entity.Item;
import com.ssafy._66days.mono.item.model.repository.InventoryRepository;
import com.ssafy._66days.mono.item.model.repository.ItemRepository;
import com.ssafy._66days.mono.user.model.entity.User;
import com.ssafy._66days.mono.user.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    public InventoryService(
            InventoryRepository inventoryRepository,
            UserRepository userRepository,
            ItemRepository itemRepository
    ) {
        this.inventoryRepository = inventoryRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public InventoryResponseDTO getInventoryInfo(UUID userId, Long itemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이템입니다"));

        Optional<Inventory> optionalInventory = inventoryRepository.findByUserAndItem(user, item);
        System.out.println(optionalInventory);
        Inventory inventory = null;
        if (optionalInventory.isEmpty()) {
            Inventory newInventory = Inventory.builder()
                    .user(user)
                    .item(item)
                    .quantity(0)
                    .build();
            inventory = inventoryRepository.save(newInventory);
        } else {
            inventory = optionalInventory.get();
        }

        return InventoryResponseDTO.of(inventory, item);
    }

}



