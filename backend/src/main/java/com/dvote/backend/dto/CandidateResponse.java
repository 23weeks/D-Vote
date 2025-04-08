package com.dvote.backend.dto;

import com.dvote.backend.entity.Candidate;

public class CandidateResponse {
	
	private Long id;
	private String name;
	private String description;
	
	public CandidateResponse(Candidate candidate) {
		this.id = candidate.getId();
		this.name = candidate.getName();
		this.description = candidate.getDescription();
	}
	
	public CandidateResponse(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
