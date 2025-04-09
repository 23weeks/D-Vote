package com.dvote.backend.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Map<String, Object>> handleCustomException(CustomException e) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", e.getStatus());
		body.put("message", e.getMessage());
		
		return ResponseEntity.status(e.getStatus()).body(body);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException e) {
	    Map<String, String> response = new HashMap<>();
	    response.put("error", "권한이 없습니다.");
	    response.put("message", "이 작업을 수행할 권한이 없습니다.");
	    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN); // 403
	}
	
	//예외 누락 방지용(전체 예외 핸들링)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGeneralException(Exception e) {
		Map<String, Object> body = new HashMap<>();
		body.put("error", "서버 내부 오류가 발생했습니다.");
		body.put("message", e.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}
}
