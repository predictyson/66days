package com.ssafy._66days.notificationservice;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="notification")
public class Notification {
    @Id
    private String id;
    private String msg;
    private Integer user_id;
    private String createdAt;
}
