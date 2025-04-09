package com.dvote.backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvote.backend.Exception.CustomException;
import com.dvote.backend.dto.request.VoterRequest;
import com.dvote.backend.dto.response.VoterResponse;
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
		Boolean isAdmin = request.isAdmin();
				
		//중복 투표 검사
		if(voterRepository.existsByVoterNameAndVoteId(voterName, voteId)) {
			throw new CustomException("이미 해당 투표에 참여했습니다.", 401);
		}
		
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new CustomException("유효하지 않은 투표 ID입니다.", 401));
		
		//투표 기간 검사
		if(!vote.isActive() || LocalDateTime.now().isAfter(vote.getEndTime())) {
			throw new CustomException("해당 투표는 종료되었습니다.", 401);
		}
		
		Candidate candidate = candidateRepository.findById(candidateId)
				.orElseThrow(() -> new CustomException("유효하지 않은 후보자 ID입니다.", 401));
		
		Voter voter = new Voter(voterName, vote, candidate, isAdmin);
		return new VoterResponse(voterRepository.save(voter));
	}
}
