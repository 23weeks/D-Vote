package com.dvote.backend.dto.request;

import java.time.LocalDateTime;

public class VoteRequest {
	
	private String title;
	private String description;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private boolean isActive;
	private int voterCountTarget;
	
	private Long candidateId;
	private String VoterName;
	
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public String getVoterName() {
		return VoterName;
	}
	public boolean isActive() {
		return isActive;
	}
	public int getVoterCountTarget() {
		return voterCountTarget;
	}
}
