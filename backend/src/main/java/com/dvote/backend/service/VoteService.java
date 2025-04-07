package com.dvote.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvote.backend.dto.VoteRequest;
import com.dvote.backend.dto.VoteResponse;
import com.dvote.backend.entity.Vote;
import com.dvote.backend.repository.VoteRepository;

@Service
public class VoteService {
	
	private final VoteRepository voteRepository;
	
	public VoteService(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}
	
	//투표 Entity 조회(closeVote에 사용)
	public Vote getVoteById(Long id) {
		Optional<Vote> vote = voteRepository.findById(id);
		return vote.orElseThrow(() -> new RuntimeException("Vote not found"));
	}
	
	//투표 조회
	public VoteResponse getVote(Long voteId) {
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid vote ID"));
		
		return new VoteResponse(
				vote.getId(),
				vote.getTitle(),
				vote.getDescription(),
				vote.getStartTime(),
				vote.getEndTime()
		);
	}
	
	//모든 투표 조회
	public List<Vote> getAllVotes() {
		return voteRepository.findAll();
	}
	
	//모든 활성화된 투표 조회
	public List<Vote> getActiveVotes() {
		return voteRepository.findByIsActiveTrue();
	}
	
	//투표 생성
	@Transactional
	public Vote createVote(VoteRequest request) {
		Vote vote = new Vote(
				request.getTitle(),
				request.getDescription(),
				request.getStartTime(),
				request.getEndTime(),
				true
		);
		return voteRepository.save(vote);
	}
	
	//투표 비활성화
	public void closeVote(Long id) {
		Vote vote = getVoteById(id);
		vote.setActive(false);
		voteRepository.save(vote);
	}
}
