package com.dvote.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvote.backend.dto.VoteRequest;
import com.dvote.backend.dto.VoteResponse;
import com.dvote.backend.entity.Vote;
import com.dvote.backend.service.VoteService;

@RestController
@RequestMapping("/api/votes")
public class VoteController {
	
	private final VoteService voteService;
	
	public VoteController(VoteService voteService) {
		this.voteService = voteService;
	}

	//해당 투표 정보 조회
	@GetMapping("/{voteId}")
	public ResponseEntity<VoteResponse> getVote(@PathVariable Long voteId) {
		VoteResponse voteResponse = voteService.getVote(voteId);
		return ResponseEntity.ok(voteResponse);
	}
	
	//투표 목록 조회
	@GetMapping
	public List<Vote> getAllVotes() {
		return voteService.getAllVotes();
	}
	
	//활성화된 투표 목록 조회
	@GetMapping("/active")
	public List<Vote> getActiveVotes() {
		return voteService.getActiveVotes();
	}
	
	//투표 등록
	@PostMapping
	public ResponseEntity<VoteResponse> createVote(@RequestBody VoteRequest request) {
		
		Vote vote = voteService.createVote(request);
		VoteResponse response = new VoteResponse(
				vote.getId(),
				vote.getTitle(),
				vote.getDescription(),
				vote.getStartTime(),
				vote.getEndTime()
		);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	//투표 종료
	@PostMapping("/{id}/close")
	public void closeVote(@PathVariable Long id) {
		voteService.closeVote(id);
	}
}