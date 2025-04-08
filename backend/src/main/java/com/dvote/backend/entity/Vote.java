package com.dvote.backend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "votes")
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	@Column(nullable = false)
	private boolean isActive;
	
	private int voterCountTarget; //전체 유권자 수
	
	@OneToMany(mappedBy = "vote", cascade = CascadeType.ALL)
	private List<Candidate> candidates;
	
	protected Vote() {
		
	}
	
	public Vote(String title, String description, LocalDateTime startTime, LocalDateTime endTime, boolean isActive, int voterCountTarget) {
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isActive = isActive;
		this.voterCountTarget = voterCountTarget;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndDate(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public int getVoterCountTarget() {
		return voterCountTarget;
	}
}
