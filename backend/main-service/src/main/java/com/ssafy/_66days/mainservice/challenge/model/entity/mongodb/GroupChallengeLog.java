package com.ssafy._66days.mainservice.challenge.model.entity.mongodb;

import com.ssafy._66days.mainservice.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mainservice.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "groupChallengeLog")
public class GroupChallengeLog {
    @Id
    private Long groupChallengeId;

    private UUID userId;
    private LocalDate time;

}
