package com.ssafy._66days.mainservice.challenge.model.entity.mongodb;

import com.ssafy._66days.mainservice.challenge.model.entity.MyChallenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "personal_challenge_log")
public class PersonalChallengeLog {
    @Id
    private Long personalChallengeId;

    private Date time;
}
