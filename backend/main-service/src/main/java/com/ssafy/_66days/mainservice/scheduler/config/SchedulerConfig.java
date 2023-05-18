package com.ssafy._66days.mainservice.scheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

	@Scheduled(cron = "0 0 1 * * *")
	public void calcRank() {
		/*

			<3번 처리 순서>
			랭킹 정산
		 */


		/**
		 * 랭킹 필요한 것: 탑3 가져오기, 이메일로 가져오기, 랭킹 저장하기
		 *
		 * 랭킹 저장하는 것은 유저 다 가져온 다음, 랭킹을 포인트에 대해 내림차순 정렬해서 가져온 다음 저장해야.
		 * UUID로 RANK REPOSITORY에서 가져오기
		 */
	}
}
