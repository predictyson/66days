package com.ssafy._66days.mainservice.scheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

	@Scheduled(cron = "0 0 5 * * *")
	public void calcRank() {
		/*
			<1번 처리 순서>
			개인 챌린지
			1. rdb에서 개인 챌린지 actiavted 상태인 것만 가져오기.
			2. 어제 로그 있으면 => 성공으로 바꾸고 경험치 산정
			       오늘이 종료일이면 => 경험치 추가 산정, 개인 업적에서 뱃지 추가
			   어제 로그 없으면 => 실패로 바꾸고 경험치 산정 (그리고.. 실패인데도 개인 업적에 뱃지 추가?)

			<2번 처리 순서>
			그룹 챌린지
			0. rdb에서 각 그룹 챌린지 actiavted, waiting 상태인 것만 가져오기.
			        >>> 회원 아이디로 그룹 챌린지 참여자 테이블에서 그룹 챌린지id 모두 가져오고,
			            그룹 챌린지id로 그룹 챌린지 모두 검색.
			1. waiting 상태인 것: 날짜가 오늘이면 activate로 설정
			2. activated 중이면
			     참여자 리스트를 모두 가져온다.
			     참여자 각 리스트로 그룹 챌린지 어제 로그 검색 => 있는 사람은 경험치 각각 주기 and 갯수 세기
			     참여자수 - 갯수 >= 프리징 수 이면 프리징 수 깎고 pass
			       오늘이 종료일이면 => 경험치 추가 산정, 그룹 업적에서 뱃지 추가
			     참여자수 - 갯수 < 프리징 수 이면 failed로 전부 만들고 경험치 산정 (그리고.. 실패인데도 그룹 업적에 뱃지 추가?)

			<3번 처리 순서>
			랭킹 정산


			이후 방법은 아래 "랭킹 저장하는 것은..."
		 */


		/**
		 * 랭킹 필요한 것: 탑3 가져오기, 이메일로 가져오기, 랭킹 저장하기
		 *
		 * 탑 3 가져오기: repository에 되어있는 것으로 충분
		 * 랭킹 저장하는 것은 유저 다 가져온 다음, 랭킹을 포인트에 대해 내림차순 정렬해서 가져온 다음 저장해야.
		 * 이메일로 가져오는 것은 UUID로 RDB에서 내 아이디 조회 AND 조회한 레코드의 이메일로 RANK REPOSITORY에서 가져오기
		 */
	}
}
