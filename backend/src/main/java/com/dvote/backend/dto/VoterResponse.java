package com.dvote.backend.dto;

import com.dvote.backend.entity.Voter;

public class VoterResponse {
	
	private Long id;
	private String name;
	private String candidateName;
	
	public VoterResponse(Voter voter) {
		this.id = voter.getId();
		this.name = voter.getVoterName();
		this.candidateName = voter.getCandidate().getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCandidateName() {
		return candidateName;
	}
}
