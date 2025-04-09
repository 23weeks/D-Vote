package com.dvote.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvote.backend.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	List<Candidate> findByVoteId(Long voteId);
	boolean existsByNameAndVote_Id(String name, Long voteId);
}
