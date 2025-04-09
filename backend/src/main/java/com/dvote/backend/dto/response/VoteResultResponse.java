package com.dvote.backend.dto.response;

import java.util.List;

public class VoteResultResponse {
	
	private Long voteId;
	private List<CandidateResult> results;
	private String winner;
	
	public VoteResultResponse(Long voteId, List<CandidateResult> results, String winner) {
		this.voteId = voteId;
		this.results = results;
		this.winner = winner;
	}
	
	public Long getVoteId() {
		return voteId;
	}
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
	public List<CandidateResult> getResults() {
		return results;
	}
	public void setResults(List<CandidateResult> results) {
		this.results = results;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}

	public static class CandidateResult {
		private String candidateName;
		private long voteCount;
		private double voteRate;
		
		public CandidateResult(String candidateName, long voteCount, double voteRate) {
			this.candidateName = candidateName;
			this.voteCount = voteCount;
			this.voteRate = voteRate;
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
	}
}
