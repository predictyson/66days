package com.ssafy._66days.mono.challenge.model.entity.mongodb;

import java.time.LocalDate;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "myChallengeLog")
public class PersonalChallengeLog {
	@Id
	private Long myChallengeId;

	private LocalDate time;
}
