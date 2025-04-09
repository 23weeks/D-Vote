package com.dvote.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvote.backend.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long> {
	boolean existsByVoterNameAndVoteId(String voterName, Long voteId);
}
