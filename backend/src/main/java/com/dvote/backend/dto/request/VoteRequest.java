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
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setVoterCountTarget(int voterCountTarget) {
		this.voterCountTarget = voterCountTarget;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public void setVoterName(String voterName) {
		VoterName = voterName;
	}
}
