package com.dvote.backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvote.backend.dto.VoterRequest;
import com.dvote.backend.dto.VoterResponse;
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
	public VoterResponse registerVote(VoterRequest request) {
		
		Long voteId = request.getVoteId();
		String voterName = request.getVoterName();
		Long candidateId = request.getCandidateId();
				
		//중복 투표 검사
		if(voterRepository.existsByVoterIdAndVoteId(voteId, voterName)) {
			throw new IllegalStateException("이미 해당 투표에 참여했습니다.");
		}
		
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 투표 ID입니다."));
		
		//투표 기간 검사
		if(!vote.isActive() || LocalDateTime.now().isAfter(vote.getEndTime())) {
			throw new IllegalStateException("해당 투표는 종료되었습니다.");
		}
		
		Candidate candidate = candidateRepository.findById(candidateId)
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 후보자 ID입니다."));
		
		Voter voter = new Voter(voterName, vote, candidate);
		return new VoterResponse(voterRepository.save(voter));
	}
}
