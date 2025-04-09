package com.dvote.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvote.backend.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{
	//진행중인 투표 조회
	List<Vote> findByIsActiveTrue();
	//
	List<Vote> findByEndTimeBeforeAndIsActiveTrue(LocalDateTime now);
}
