package com.dvote.backend.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dvote.backend.service.VoteService;

@Component
public class VoteScheduler {
	
	private final VoteService voteService;
	
	public VoteScheduler(VoteService voteService) {
		this.voteService = voteService;
	}
	
	//매일 새벽 1 시에 종료된 투표 자동 반영
	@Scheduled(cron = "0 0 1 * * ?")
	public void updateExpiredVotes() {
		voteService.closeExpiredVotes();
	}
	
	/*테스트용 - 1분마다 확인
	@Scheduled(fixedRate = 60000)
	public void updateExpiredVotes() {
		voteService.closeExpiredVotes();
	}
	*/
}
