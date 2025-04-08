package com.dvote.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvote.backend.dto.CandidateRequest;
import com.dvote.backend.dto.CandidateResponse;
import com.dvote.backend.service.CandidateService;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
	
	private final CandidateService candidateService;
	
	public CandidateController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}
	
	//후보자 등록
	@PostMapping("/{voteId}")
	public ResponseEntity<CandidateResponse> createCandidate(@PathVariable Long voteId, @RequestBody CandidateRequest request) {
		
		return ResponseEntity.ok(candidateService.createCandidate(voteId, request));
	}
	
	//투표별 후보자 목록
	@GetMapping("/{voteId}/list")
	public ResponseEntity<List<CandidateResponse>> getCandidatesByVoteId(@PathVariable Long voteId) {
		
		return ResponseEntity.ok(candidateService.getCandidatesByVoteId(voteId));
	}
	
}
