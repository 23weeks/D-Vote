package com.dvote.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvote.backend.dto.CandidateRequest;
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
	
	public Candidate findById(Long id) {
		return candidateRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid candidate ID"));
	}
	
	//후보자 생성
	@Transactional
	public CandidateResponse createCandidate(Long voteId, CandidateRequest request) {
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid vote Id"));
		
		Candidate candidate = new Candidate(request.getName(), request.getDescription(), vote);
		Candidate saved = candidateRepository.save(candidate);
		
		return new CandidateResponse(saved);
	}
	
	//후보자 목록
	public List<CandidateResponse> getCandidatesByVoteId(Long voteId) {
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid vote ID"));
		
		List<Candidate> candidates = candidateRepository.findByVote(vote);
		
		return candidates.stream()
				.map(CandidateResponse::new)
				.collect(Collectors.toList());
	}
}
