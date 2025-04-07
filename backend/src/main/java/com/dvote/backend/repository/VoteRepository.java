package com.dvote.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvote.backend.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{
	List<Vote> findByIsActiveTrue();
}
