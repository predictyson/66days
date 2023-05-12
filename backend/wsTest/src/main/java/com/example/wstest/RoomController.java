package com.example.wstest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomService chatRoomService;

//    //채팅방 목록 조회
//    @GetMapping(value = "/rooms")
//    public ModelAndView rooms(){
//
//        log.info("# All Chat Rooms");
//        ModelAndView mv = new ModelAndView("chat/rooms");
//
////        mv.addObject("list", repository.findAllRooms());
//
//        return mv;
//    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestBody ChatRoomDTO chatRoomDTO){

        log.info("# Create Chat Room , name: " + chatRoomDTO.getRoomName());
//        rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(name));
        chatRoomRepository.save(chatRoomDTO);

        return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room/{roomName}")
    public ResponseEntity<Map<String, Object>> getRoom(@PathVariable String roomName, Model model){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        log.info("# get Chat Room, roomID : " + roomName);

        int pgNo = 0;
        List<ChatMessageDTO> chatMessageDTOList = chatRoomService.getRecentChats(roomName, pgNo);

        log.info("get Chat RoomDTOList: {}", chatMessageDTOList);

        resultMap.put("chatMessageDTOList", chatMessageDTOList);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
}