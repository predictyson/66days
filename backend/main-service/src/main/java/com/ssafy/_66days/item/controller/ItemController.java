package com.ssafy._66days.item.controller;

import com.ssafy._66days.global.util.AuthenticateUtil;
import com.ssafy._66days.item.model.dto.ResponseDTO.InventoryResponseDTO;
import com.ssafy._66days.item.model.dto.ResponseDTO.ItemResponseDTO;
import com.ssafy._66days.item.model.repository.InventoryRepository;
import com.ssafy._66days.item.model.repository.ItemRepository;
import com.ssafy._66days.item.model.service.InventoryService;
import com.ssafy._66days.item.model.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final InventoryService inventoryService;
    private final ItemService itemService;
    private final String userIdStr = "a817d372-ee0d-11ed-a26b-0242ac110003";
    private final UUID userId = UUID.fromString(userIdStr);

    @Autowired
    public ItemController(
            InventoryService inventoryService,
            ItemService itemService
    ) {
        this.inventoryService = inventoryService;
        this.itemService = itemService;
    }

    @GetMapping("/inventory")
    @ApiOperation(value = "프리즈 보유개수, 이미지 조회 API", notes = "프리즈 보유개수와 프리즈 이미지를 반환")
    public ResponseEntity<Map<String, Object>> getInventory(
//            @RequestHeader(name = "Authorization") String accessToken
            Long itemId
    ) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // auth서버로 인증 요청
//            AuthenticateUtil authenticateUtil = new AuthenticateUtil();
//            UUID userId = authenticateUtil.getUserId(accessToken);

            InventoryResponseDTO inventoryResponseDTO = inventoryService.getInventoryInfo(userId, itemId);
            resultMap.put("inventoryResponseDTO", inventoryResponseDTO);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/infomation/{item_id}")
    @ApiOperation(value = "프리즈 가격, 보유 포인트 조회 API", notes = "구매하기 누르면 프리즈 가격, 보유 포인트를 반환")
    public ResponseEntity<Map<String, Object>> getItem(
            @PathVariable("item_id") Long itemId
//            @RequestHeader(name = "Authorization") String accessToken
    ) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // auth서버로 인증 요청
//            AuthenticateUtil authenticateUtil = new AuthenticateUtil();
//            UUID userId = authenticateUtil.getUserId(accessToken);

            ItemResponseDTO itemResponseDTO = itemService.getItemInfo(userId, itemId);
            resultMap.put("itemResponseDTO", itemResponseDTO);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/purchase/{item_id}")
    @ApiOperation(value = "아이템 구매 API", notes = "아이템을 구매하면 보유 poibnt와 가격 비교, 아이템 개수 반환")
    public ResponseEntity<Map<String, Object>> purchaseItem(
            @PathVariable("item_id") Long itemId
//            @RequestHeader(name = "Authorization") String accessToken
    ) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // auth서버로 인증 요청
//            AuthenticateUtil authenticateUtil = new AuthenticateUtil();
//            UUID userId = authenticateUtil.getUserId(accessToken);

            int quantity = itemService.buyItem(userId, itemId);
            resultMap.put("quantity", quantity);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
