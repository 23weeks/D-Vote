package com.dvote.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvote.backend.entity.User;
import com.dvote.backend.entity.Vote;
import com.dvote.backend.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long> {
	//투표 완료 체크
	boolean existsByVoterNameAndVoteId(String voterName, Long voteId);
	
	//특정 투표 모든 유권자 조회
	List<Voter> findByVote(Vote vote);
	
	//로그인 유저 투표 이력 조회
	List<Voter> findByVoterName(User user);
	
	boolean existsByUserAndVote(User user, Vote vote);
}
