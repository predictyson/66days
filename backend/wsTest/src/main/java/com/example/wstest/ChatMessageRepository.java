
package com.example.wstest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessageDTO, String> {
    List<ChatMessageDTO> findAllByRoomId(String roomId, Pageable pageable);

//    List<ChatMessageDTO> findAllByRoomId(String roomId);
}
