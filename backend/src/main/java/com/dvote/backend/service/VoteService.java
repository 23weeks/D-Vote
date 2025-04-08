package com.dvote.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvote.backend.dto.VoteRequest;
import com.dvote.backend.dto.VoteResponse;
import com.dvote.backend.dto.VoteResultResponse;
import com.dvote.backend.entity.Candidate;
import com.dvote.backend.entity.Vote;
import com.dvote.backend.entity.Voter;
import com.dvote.backend.repository.VoteRepository;

@Service
public class VoteService {
	
	private final VoteRepository voteRepository;
	
	public VoteService(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}
	
	public Vote findById(Long id) {
		return voteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid vote ID"));
	}
	
	//투표 생성
	@Transactional
	public Vote createVote(VoteRequest request) {
		Vote vote = new Vote(
				request.getTitle(),
				request.getDescription(),
				request.getStartTime(),
				request.getEndTime(),
				true,
				request.getVoterCountTarget()
		);
		
		return voteRepository.save(vote);
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
	public List<VoteResponse> getAllVotes() {
		return voteRepository.findAll().stream()
				.map(VoteResponse::new)
				.collect(Collectors.toList());
	}
	
	//투표 집계
	public List<VoteResultResponse> getVoteResults(Long voteId) {
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid vote ID"));
		
		List<Candidate> candidates = vote.getCandidates();
		
		//총 투표자 수 (중복 방지 위해 유니크하게 계산)
		Set<Voter> totalVoters = new HashSet<>();
		for(Candidate c : candidates) {
			totalVoters.addAll(c.getVoters());
		}
		
		int totalVoteCount = totalVoters.size();
		int totalVoterTargetCount = vote.getVoterCountTarget();
		
		return candidates.stream()
				.map(c -> {
					int count = c.getVoters().size();
					double voterRate = totalVoteCount == 0 ? 0 : (double) count * 100 / totalVoteCount;
					double turnoutRate = totalVoterTargetCount == 0 ? 0 : (double) totalVoteCount * 100 / totalVoterTargetCount;
					
					return new VoteResultResponse(c.getName(), totalVoteCount, voterRate, turnoutRate);
				})
				.collect(Collectors.toList());
	}
}
