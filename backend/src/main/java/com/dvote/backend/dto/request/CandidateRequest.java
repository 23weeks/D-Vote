package com.dvote.backend.dto.request;

public class CandidateRequest {

	private String name;
	private String description;
	private Long voteId;
	
	public Long getVoteId() {
		return voteId;
	}
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
}
