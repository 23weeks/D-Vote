package com.dvote.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvote.backend.dto.request.VoterRequest;
import com.dvote.backend.dto.response.VoterResponse;
import com.dvote.backend.service.VoterService;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

	private final VoterService voterService;
	
	public VoterController(VoterService voterService) {
		this.voterService = voterService;
	}
	
	@PostMapping
	public ResponseEntity<VoterResponse> vote(@RequestBody VoterRequest request) {
		return ResponseEntity.ok(voterService.registerVote(request));
	}
}
