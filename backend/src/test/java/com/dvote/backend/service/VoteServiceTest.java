package com.dvote.backend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dvote.backend.dto.request.VoteRequest;
import com.dvote.backend.entity.Vote;
import com.dvote.backend.repository.VoteRepository;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {
	
	@Mock
	private VoteRepository voteRepository;
	
	@InjectMocks
	private VoteService voteService;
	
	@Test
	public void createVote_test() {
		
		//given
		VoteRequest request = new VoteRequest();
		request.setTitle("테스트 투표");
		request.setDescription("테스트 설명");
		request.setStartTime(LocalDateTime.now());
		request.setEndTime(LocalDateTime.now().plusDays(1));
		request.setActive(true);
		
		Vote vote = new Vote(
				request.getTitle(),
				request.getDescription(),
				request.getStartTime(),
				request.getEndTime(),
				request.isActive()
		);
		
		when(voteRepository.save(any(Vote.class))).thenReturn(vote);
		
		//when
		Vote result = voteService.createVote(request);
		
		//then
		assertThat(result.getTitle()).isEqualTo("테스트 투표");
		verify(voteRepository).save(any(Vote.class));
	}
}
