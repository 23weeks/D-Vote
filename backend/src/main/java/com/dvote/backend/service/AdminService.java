package com.dvote.backend.service;

import org.springframework.stereotype.Service;

import com.dvote.backend.dto.response.AdminStatisticsResponse;
import com.dvote.backend.repository.UserRepository;
import com.dvote.backend.repository.VoteRepository;
import com.dvote.backend.repository.VoterRepository;

@Service
public class AdminService {
	
	private final UserRepository userRepository;
	private final VoteRepository voteRepository;
	private final VoterRepository voterRepository;
	
	public AdminService(UserRepository userRepository, VoteRepository voteRepository, VoterRepository voterRepository) {
		this.userRepository = userRepository;
		this.voteRepository = voteRepository;
		this.voterRepository = voterRepository;
	}
	
	public AdminStatisticsResponse getStatistics() {
		long userCount = userRepository.count();
		long voteCount = voteRepository.count();
		long participationCount = voterRepository.count();
		
		return new AdminStatisticsResponse(userCount, voteCount, participationCount);
	}
}
