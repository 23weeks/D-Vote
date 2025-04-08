package com.dvote.backend.dto;

public class VoteResultResponse {
	
	private String candidateName;
	private long voteCount;
	private double voteRate; //후보 득표율
	private double turnoutRate; //전체 투표율
	
	public VoteResultResponse(String candidateName, long voteCount, double voteRate, double turnoutRate) {
		this.candidateName = candidateName;
		this.voteCount = voteCount;
		this.voteRate = voteRate;
		this.turnoutRate = turnoutRate;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}

	public double getVoteRate() {
		return voteRate;
	}

	public void setVoteRate(double voteRate) {
		this.voteRate = voteRate;
	}

	public double getTurnoutRate() {
		return turnoutRate;
	}

	public void setTurnoutRate(double turnoutRate) {
		this.turnoutRate = turnoutRate;
	}
}
