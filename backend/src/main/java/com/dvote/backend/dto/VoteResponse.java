package com.dvote.backend.dto;

import java.time.LocalDateTime;

import com.dvote.backend.entity.Vote;

public class VoteResponse {
	
	private Long id;
	private String title;
	private String description;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private boolean isActive;

	public VoteResponse(Vote vote) {
		this.id = vote.getId();
		this.title = vote.getTitle();
		this.startTime = vote.getStartTime();
		this.endTime = vote.getEndTime();
		this.isActive = vote.isActive();
	}
	
	public VoteResponse(Long id, String title, String description, LocalDateTime startTime, LocalDateTime endTime, boolean isActive) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

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
	
	public boolean isActive() {
		return isActive;
	}
}
