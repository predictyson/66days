package com.ssafy._66days.item.model.service;

import com.ssafy._66days.item.model.dto.ResponseDTO.ItemResponseDTO;
import com.ssafy._66days.item.model.entity.Inventory;
import com.ssafy._66days.item.model.entity.Item;
import com.ssafy._66days.item.model.repository.InventoryRepository;
import com.ssafy._66days.item.model.repository.ItemRepository;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ItemService {
    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemService(
            InventoryRepository inventoryRepository,
            ItemRepository itemRepository,
            UserRepository userRepository
    ) {
        this.inventoryRepository = inventoryRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }
    public ItemResponseDTO getItemInfo(UUID userId, Long itemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이템입니다"));


        return ItemResponseDTO.of(user, item);
    }

    @Transactional
    public int buyItem(UUID userId, Long itemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이템입니다"));
        Inventory inventory = inventoryRepository.findByUserAndItem(user, item)
                .orElseThrow(() -> new IllegalArgumentException("인벤토리 정보를 찾을 수 없습니다"));

        int price = item.getPrice();        // 프리즈 아이템의 가격 정보
        Long priceLong = new Long(price);   // Long으로 형변환
        Long gotPoint = user.getPoint();    // 보유 포인트 정보

        // 보유 포인트 보다 아이템 가격이 비싸면 못산다
        if (priceLong > gotPoint) {
            throw new IllegalArgumentException("보유 포인트가 부족합니다");
        }
        Long point = gotPoint - priceLong; // 보유point - 아이템 가격
        log.info("user point BEFORE: {}", user.getPoint());

        user.updatePoint(point);    // 구매 후 보유 point로 update

        log.info("user point AFTER: {}", user.getPoint());
        log.info("iq BEFORE: {}", inventory.getQuantity());

        inventory.updateQuantity(); // 아이템 수량 ++
        log.info("iq AFTER: {}", inventory.getQuantity());
        int quantity = inventory.getQuantity();

        return quantity;
    }
}
