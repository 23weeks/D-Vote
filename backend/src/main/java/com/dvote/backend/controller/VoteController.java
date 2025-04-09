package com.dvote.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvote.backend.dto.request.VoteRequest;
import com.dvote.backend.dto.response.VoteHistoryResponse;
import com.dvote.backend.dto.response.VoteResponse;
import com.dvote.backend.dto.response.VoteResultResponse;
import com.dvote.backend.entity.Vote;
import com.dvote.backend.service.VoteService;


@RestController
@RequestMapping("/api/votes")
public class VoteController {
	
	private final VoteService voteService;
	
	public VoteController(VoteService voteService) {
		this.voteService = voteService;
	}

	//투표 등록
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<VoteResponse> createVote(@RequestBody VoteRequest request) {
		
		Vote vote = voteService.createVote(request);
		VoteResponse response = new VoteResponse(
				vote.getId(),
				vote.getTitle(),
				vote.getDescription(),
				vote.getStartTime(),
				vote.getEndTime(),
				true
		);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	//해당 투표 정보 조회
	@GetMapping("/{voteId}")
	public ResponseEntity<VoteResponse> getVote(@PathVariable Long voteId) {
		return ResponseEntity.ok(voteService.getVote(voteId));
	}
	
	//투표 목록 조회
	@GetMapping
	public ResponseEntity<List<VoteResponse>> getAllVotes() {
		return ResponseEntity.ok(voteService.getAllVotes());
	}
	
	//진행중인 투표 조회
	@GetMapping("/isActive")
	public ResponseEntity<List<VoteResponse>> getActiveVotes() {
		return ResponseEntity.ok(voteService.getActiveVotes());
	}
	
	//투표 종료
	@PutMapping("/{voteId}/close")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> closeVote(@PathVariable Long voteId) {
		voteService.closeVote(voteId);
		return ResponseEntity.ok("Vote closed succesfully.");
	}
	
	//투표 결과 조회
	@GetMapping("/{voteId}/result")
	public ResponseEntity<VoteResultResponse> getVoteResult(@PathVariable Long voteId) {
		return ResponseEntity.ok(voteService.getVoteResult(voteId));
	}
	
	//로그인 유저 투표 이력 조회
	@GetMapping("/history")
	public ResponseEntity<List<VoteHistoryResponse>> getUserVoteHistory(@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok((voteService.getuserVoteHistory(userDetails.getUsername())));
	}
	
	//사용자가 특정 투표에 참여했는지 체크
	@GetMapping("/{voteId}/participation")
	public ResponseEntity<Map<String, Boolean>> checkParticipation(@PathVariable Long voteId, @AuthenticationPrincipal UserDetails userDetails) {
		
		boolean hasParticipated = voteService.hasUserParticipated(voteId, userDetails.getUsername());
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("participated", hasParticipated);
		
		return ResponseEntity.ok(response);
	}
}