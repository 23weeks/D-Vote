package com.dvote.backend.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvote.backend.Exception.CustomException;
import com.dvote.backend.dto.request.VoteRequest;
import com.dvote.backend.dto.response.VoteHistoryResponse;
import com.dvote.backend.dto.response.VoteResponse;
import com.dvote.backend.dto.response.VoteResultResponse;
import com.dvote.backend.dto.response.VoteResultResponse.CandidateResult;
import com.dvote.backend.entity.Candidate;
import com.dvote.backend.entity.User;
import com.dvote.backend.entity.Vote;
import com.dvote.backend.entity.Voter;
import com.dvote.backend.repository.UserRepository;
import com.dvote.backend.repository.VoteRepository;
import com.dvote.backend.repository.VoterRepository;

@Service
public class VoteService {
	
	private final VoteRepository voteRepository;
	private final VoterRepository voterRepository;
	private final UserRepository userRepository;
	
	public VoteService(VoteRepository voteRepository, VoterRepository voterRepository, UserRepository userRepository) {
		this.voteRepository = voteRepository;
		this.voterRepository = voterRepository;
		this.userRepository = userRepository;
	}
	
	//투표 생성
	@Transactional
	public Vote createVote(VoteRequest request) {
		Vote vote = new Vote(
				request.getTitle(),
				request.getDescription(),
				LocalDateTime.now(),
				request.getEndTime(),
				true
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
				vote.getEndTime(),
				vote.isActive()
		);
	}
	
	//모든 투표 조회
	public List<VoteResponse> getAllVotes() {
		return voteRepository.findAll().stream()
				.map(VoteResponse::new)
				.collect(Collectors.toList());
	}
	
	//투표 종료
	@Transactional
	public void closeVote(Long voteId) {
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new CustomException("사용자를 찾을 수 없습니다.", 401));
		
		if(!vote.isActive()) {
			throw new CustomException("이미 종료된 투표입니다.", 401);
		}
		
		vote.setActive(false);
		vote.setEndTime(LocalDateTime.now());
		voteRepository.save(vote);
	}
	
	//진행중인 투표 조회
	public List<VoteResponse> getActiveVotes() {
		List<Vote> activeVotes = voteRepository.findByIsActiveTrue();
		
		return activeVotes.stream()
				.map(vote -> new VoteResponse(
						vote.getId(),
						vote.getTitle(),
						vote.getDescription(),
						vote.getStartTime(),
						vote.getEndTime(),
						vote.isActive()
				))
				.collect(Collectors.toList());
	}
	
	//투표 자동종료 처리 메서드 추가
	@Transactional
	public void closeExpiredVotes() {
		List<Vote> expiredVotes = voteRepository.findByEndTimeBeforeAndIsActiveTrue(LocalDateTime.now());
		
		for(Vote vote : expiredVotes) {
			vote.setActive(false);
		}
		
		voteRepository.saveAll(expiredVotes);
	}
	
	//투표 결과 계산
	public VoteResultResponse getVoteResult(long voteId) {
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new CustomException("투표 정보를 찾을 수 없습니다.", 401));
		
		//해당 투표에 속한 유권자 조회
		List<Voter> voters = voterRepository.findByVote(vote);
		
		if(voters.isEmpty()) {
			return new VoteResultResponse(voteId, Collections.emptyList(), "투표 없음");
		}
		
		//후보자별 득표수 계산
		Map<Candidate, Long> voteCountMap = voters.stream()
				.filter(v -> v.getCandidate() != null)
				.collect(Collectors.groupingBy(Voter::getCandidate, Collectors.counting()));
		
		//전체 투표수
		long totalVotes = voteCountMap.values().stream().mapToLong(Long::longValue).sum();
		
		List<CandidateResult> resultList = voteCountMap.entrySet().stream()
				.map(entry -> new CandidateResult(
						entry.getKey().getName(),
						entry.getValue(),
						Math.round(((double) entry.getValue() / totalVotes) * 1000.0) /10.0
				))
				.sorted((a, b) -> Long.compare(b.getVoteCount(), a.getVoteCount()))
				.collect(Collectors.toList());
		
		String winner = resultList.get(0).getCandidateName();
		
		return new VoteResultResponse(voteId, resultList, winner);
	}
	
	//로그인 유저 투표 이력 조회
	public List<VoteHistoryResponse> getuserVoteHistory(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new CustomException("사용자를 찾을 수 없습니다.", 401));
		
		List<Voter> voters = voterRepository.findByVoterName(user);
		
		return voters.stream()
				.map(voter -> new VoteHistoryResponse(
						voter.getVote().getTitle(),
						voter.getCandidate().getName(),
						voter.getVote().getEndTime()
				))
				.collect(Collectors.toList());
	}
	
	//투표 유무 체크
	public boolean hasUserParticipated(Long voteId, String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new CustomException("사용자를 찾을 수 없습니다.", 401));
		
		Vote vote = voteRepository.findById(voteId)
				.orElseThrow(() -> new CustomException("투표를 찾을 수 없습니다.", 401));
		
		return voterRepository.existsByUserAndVote(user, vote);
	}
	
}
