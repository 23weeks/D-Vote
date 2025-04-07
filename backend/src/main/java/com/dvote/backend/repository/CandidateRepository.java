package com.dvote.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvote.backend.entity.Candidate;
import com.dvote.backend.entity.Vote;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	List<Candidate> findByVoteId(Long voteId);
	List<Candidate> findByVote(Vote vote);
}
