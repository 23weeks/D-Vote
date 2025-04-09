package com.dvote.backend.dto.response;

import java.time.LocalDateTime;

public class VoteHistoryResponse {
	private String voteTitle;
	private String candidateName;
	private LocalDateTime voteDeadline;
	
	public VoteHistoryResponse(String voteTitle, String candidateName, LocalDateTime voteDeadline) {
		this.voteTitle = voteTitle;
		this.candidateName = candidateName;
		this.voteDeadline = voteDeadline;
	}
	
	public String getVoteTitle() {
		return voteTitle;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public LocalDateTime getVoteDeadline() {
		return voteDeadline;
	}
}
