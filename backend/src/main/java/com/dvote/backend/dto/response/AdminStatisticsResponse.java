package com.dvote.backend.dto.response;

public class AdminStatisticsResponse {

	private long userCount;
	private long voteCount;
	private long participationCount;
	
	public AdminStatisticsResponse(long userCount, long voteCount, long participationCount) {
		this.userCount = userCount;
		this.voteCount = voteCount;
		this.participationCount = participationCount;
	}

	public long getUserCount() {
		return userCount;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public long getParticipationCount() {
		return participationCount;
	}
}
