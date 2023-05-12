
package com.example.wstest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoomDTO, String> {
//    List<ChatRoomDTO> findAllRooms();
    ChatRoomDTO findRoomByRoomName(String name);

//    List<ChatRoomDTO> findAllByRoomId(String roomId, Pageable pageable);
//    ChatRoomDTO createChatRoomDTO(String name);
}
