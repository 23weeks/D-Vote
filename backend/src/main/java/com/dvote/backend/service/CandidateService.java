package com.dvote.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvote.backend.dto.CandidateResponse;
import com.dvote.backend.entity.Candidate;
import com.dvote.backend.entity.Vote;
import com.dvote.backend.repository.CandidateRepository;
import com.dvote.backend.repository.VoteRepository;

@Service
public class CandidateService {
	
	private final CandidateRepository candidateRepository;
	private final VoteRepository voteRepository;
	
	public CandidateService(CandidateRepository candidateRepository, VoteRepository voteRepository) {
		this.candidateRepository = candidateRepository;
		this.voteRepository = voteRepository;
	}
	
	public List<CandidateResponse> getCandidatesByVoteId(Long voteId) {
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid vote ID"));
		
		List<Candidate> candidates = candidateRepository.findByVote(vote);
		
		return candidates.stream()
				.map(candidate -> new CandidateResponse(
						candidate.getId(),
						candidate.getName(),
						candidate.getDescription()
						))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public Candidate createCandidate(Long voteId, String name, String description) {
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid vote Id"));
		
		Candidate candidate = new Candidate(name, description, vote);
		return candidateRepository.save(candidate);
	}
}
