package com.example.wstest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Document(collection  = "chatmessage")
@ToString
public class ChatMessageDTO {

    /*
    roomId:
    type: "ENTER" | "CHAT" | "LEAVE"; // 메시지 타입
    nickname:  string; // 유저 닉네임
    image: string; // 유저 프로필 이미지 경로
    value: string; // 메시지 내용
    date: string; // 보낸 시간
     */

    @Id
    private String id;
    private String roomId;
    private String type;
    private String nickname;
    private String value;
    private Date date;
}