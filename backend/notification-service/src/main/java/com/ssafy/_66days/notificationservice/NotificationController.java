package com.ssafy._66days.notificationservice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@RequestMapping("/notification")
@RestController //데이터 리턴 서버
public class NotificationController {
    private final NotificationRepository notificationRepository;
    @CrossOrigin
    @GetMapping(value="/{userId}",produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Notification> findListByUserId(@PathVariable(value = "userId") Integer userId){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        return notificationRepository.mFindListByUserId(userId, pageable)
                .subscribeOn(Schedulers.boundedElastic());
    }
    @CrossOrigin
    @GetMapping(value="/list/{userId}",produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Notification> findByUserId(@PathVariable(value = "userId") Integer userId){
        return notificationRepository.mFindByUserId(userId)
                .subscribeOn(Schedulers.boundedElastic());
    }
    @CrossOrigin
    @PostMapping("")
    public Mono<Notification> setMsg(@RequestBody Notification chat){
        chat.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toString());
        return notificationRepository.save(chat);
    }
}
