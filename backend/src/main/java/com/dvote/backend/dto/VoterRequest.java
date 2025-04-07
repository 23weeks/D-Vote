package com.dvote.backend.dto;

public class VoterRequest {

	private String voterName;
	private Long voteId;
	private Long candidateId;
	
	protected VoterRequest() {
		
	}
	
	public VoterRequest(String voterName, Long voteId, Long candidateId) {
		this.voterName = voterName;
		this.voteId = voteId;
		this.candidateId = candidateId;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
}
