package com.dvote.backend.dto;

import java.time.LocalDateTime;

public class VoteRequest {
	
	private String title;
	private String description;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
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
}
