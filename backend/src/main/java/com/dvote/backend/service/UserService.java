package com.dvote.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dvote.backend.Exception.CustomException;
import com.dvote.backend.config.JwtTokenProvider;
import com.dvote.backend.dto.request.LoginRequest;
import com.dvote.backend.dto.request.SignupRequest;
import com.dvote.backend.dto.response.LoginResponse;
import com.dvote.backend.entity.User;
import com.dvote.backend.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	//회원가입
	public User signup(SignupRequest request) {
		if(userRepository.existsByUsername(request.getUsername())) {
			throw new CustomException("이미 사용 중인 사용자 이름입니다.", 403);
		}
		
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setAdmin(request.isAdmin());
		
		return userRepository.save(user);
	}
	
	//로그인
	public LoginResponse login(LoginRequest request) {
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new CustomException("이름 또는 비밀번호가 잘못되었습니다.", 401));
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new CustomException("이름 또는 비밀번호가 잘못되었습니다.", 401);
		}
		
		String token = jwtTokenProvider.createToken(user.getUsername(), user.isAdmin());
		
		return new LoginResponse(user.getUsername(), token);
	}
}
