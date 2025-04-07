package com.dvote.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvote.backend.dto.VoterRequest;
import com.dvote.backend.entity.Voter;
import com.dvote.backend.service.VoterService;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

	private final VoterService voterService;
	
	public VoterController(VoterService voterService) {
		this.voterService = voterService;
	}
	
	@PostMapping
	public ResponseEntity<Voter> vote(@RequestBody VoterRequest request) {
		
		Voter voter = voterService.registerVote(request);
		
		return ResponseEntity.ok(voter);
	}
}
