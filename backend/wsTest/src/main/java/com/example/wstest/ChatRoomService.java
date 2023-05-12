package com.example.wstest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatMessageRepository chatMessageRepository;
    public List<ChatMessageDTO> getRecentChats(String roomId, int pgNo){
        Pageable pageable = PageRequest.of(pgNo, 100, Sort.by("date").ascending());
//        Pageable pageable = PageRequest.of(pgNo, 100);
        List<ChatMessageDTO> chatRoomDTOList = chatMessageRepository.findAllByRoomId(roomId,pageable);
//        List<ChatMessageDTO> chatRoomDTOList = chatMessageRepository.findAllByRoomId(roomId);
        log.info("chatRoomDTOLIST:: {}", chatRoomDTOList);

        return chatRoomDTOList;
    }
}
