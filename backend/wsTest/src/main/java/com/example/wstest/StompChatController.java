package com.example.wstest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StompChatController {

    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
//    @MessageMapping("/room")
//    @SendTo("/chat/enter")
    @MessageMapping(value = "/message")
    public void enter(@Payload ChatMessageDTO message){
        System.out.println("here is message :" + message);
//        message.setValue(message.getNickname() + "님이 채팅방에 참여하였습니다.");
        if(message.getType().equals("CHAT")){
            chatMessageRepository.save(message);
        }
//        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        template.convertAndSend("/sub/challenges/"+ message.getRoomId(), message);
    }
}