package com.dvote.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvote.backend.Exception.CustomException;
import com.dvote.backend.dto.request.CandidateRequest;
import com.dvote.backend.dto.response.CandidateResponse;
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
		if(candidateRepository.existsByNameAndVote_Id(request.getName(), request.getVoteId())) {
			throw new CustomException("해당 투표에 이미 등록된 후보입니다.", 401);
		}
		
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new CustomException("투표 정보를 찾을 수 없습니다.", 404));
		
		Candidate candidate = new Candidate(request.getName(), request.getDescription(), vote);
		Candidate saved = candidateRepository.save(candidate);
		
		return new CandidateResponse(saved);
	}
	
	//후보자 목록
	public List<CandidateResponse> getCandidatesByVoteId(Long voteId) {
		List<Candidate> candidates = candidateRepository.findByVoteId(voteId);
		return candidates.stream()
				.map(CandidateResponse::new)
				.collect(Collectors.toList());
	}
}
