package com.dvote.backend.dto.response;

import com.dvote.backend.entity.Voter;

public class VoterResponse {
	
	private Long id;
	private String name;
	private String candidateName;
	private Long voteId;
	
	public VoterResponse(Voter voter) {
		this.id = voter.getId();
		this.name = voter.getVoterName();
		this.candidateName = voter.getCandidate().getName();
		this.voteId = voter.getVote().getId();
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

	public Long getVoteId() {
		return voteId;
	}
}
