package com.dvote.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvote.backend.dto.VoterRequest;
import com.dvote.backend.entity.Candidate;
import com.dvote.backend.entity.Vote;
import com.dvote.backend.entity.Voter;
import com.dvote.backend.repository.CandidateRepository;
import com.dvote.backend.repository.VoteRepository;
import com.dvote.backend.repository.VoterRepository;

@Service
public class VoterService {
	
	private final VoterRepository voterRepository;
	private final CandidateRepository candidateRepository;
	private final VoteRepository voteRepository;
	
	public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository, VoteRepository voteRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
		this.voteRepository = voteRepository;
	}
	
	//투표자 등록
	@Transactional
	public Voter registerVote(VoterRequest request) {
		Vote vote = voteRepository.findById(request.getVoteId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid vote ID"));
		
		if(!vote.isActive()) {
			throw new IllegalStateException("Voting is not active.");
		}
		
		Candidate candidate = candidateRepository.findById(request.getCandidateId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid candidate ID"));
		
		Voter voter = new Voter(request.getVoterName(), vote, candidate);
		return voterRepository.save(voter);
	}
}
