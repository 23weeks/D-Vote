package com.dvote.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvote.backend.dto.request.LoginRequest;
import com.dvote.backend.dto.request.SignupRequest;
import com.dvote.backend.dto.response.LoginResponse;
import com.dvote.backend.dto.response.SignupResponse;
import com.dvote.backend.entity.User;
import com.dvote.backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(userService.login(request));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request) {
		User user = userService.signup(request);
		SignupResponse response = new SignupResponse(
				user.getUsername(),
				user.getPassword(),
				user.isAdmin()
		);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
				
	}
}
