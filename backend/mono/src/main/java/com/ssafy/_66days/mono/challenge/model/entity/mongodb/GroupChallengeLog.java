package com.ssafy._66days.mono.challenge.model.entity.mongodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
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
