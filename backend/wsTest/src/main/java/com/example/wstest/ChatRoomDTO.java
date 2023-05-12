package com.example.wstest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Document(collection  = "chatting")
public class ChatRoomDTO {

    @Id
    private String roomId;
    private String roomName;
//    private Set<WebSocketSession> sessions = new HashSet<>();
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

    public static ChatRoomDTO create(String name){
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomId = UUID.randomUUID().toString(); //TODO: change to challege id
        room.roomName = name;
        return room;
    }
}