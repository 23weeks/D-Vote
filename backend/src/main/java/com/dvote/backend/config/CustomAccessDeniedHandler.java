package com.dvote.backend.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.warn(">>> Access denied: {}", accessDeniedException.getMessage());
		
		response.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
		response.setContentType("application/json;charset=UTF-8");
		
		String json = "{\"error\": \"권한이 없습니다.\", \"message\": \"이 작업을 수행할 권한이 없습니다.\"}";
		response.getWriter().write(json);
	}
}
