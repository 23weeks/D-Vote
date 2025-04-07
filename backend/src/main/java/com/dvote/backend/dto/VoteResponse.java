package com.dvote.backend.dto;

import java.time.LocalDateTime;

public class VoteResponse {
	
	private Long id;
	private String title;
	private String descripiton;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public VoteResponse(Long id, String title, String descripiton, LocalDateTime startTime, LocalDateTime endTime) {
		this.id = id;
		this.title = title;
		this.descripiton = descripiton;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescripiton() {
		return descripiton;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}
}
