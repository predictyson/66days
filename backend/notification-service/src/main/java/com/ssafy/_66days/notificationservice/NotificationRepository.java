package com.ssafy._66days.notificationservice;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;


public interface NotificationRepository extends ReactiveMongoRepository<Notification,String> {

    @Tailable
    @Query("{ 'user_id' : ?0 }")
    Flux<Notification> mFindByUserId(Integer userId);

    @Query("{ 'user_id' : ?0 }")
    Flux<Notification> mFindListByUserId(Integer userId, Pageable pageable);
}
