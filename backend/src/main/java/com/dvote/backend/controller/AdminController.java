package com.dvote.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvote.backend.dto.response.AdminStatisticsResponse;
import com.dvote.backend.service.AdminService;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AdminStatisticsResponse> getStatistics() {
		return ResponseEntity.ok(adminService.getStatistics());
	}
}
