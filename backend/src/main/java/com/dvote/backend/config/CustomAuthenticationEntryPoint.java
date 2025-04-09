package com.dvote.backend.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println(">>> AuthenticationEntryPoint trtiggered!");
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
		response.setContentType("application/json;charset=UTF-8");
		
		String json = "{\"error\": \"인증 실패\", \"message\": \"로그인이 필요합니다.\"}";
        response.getWriter().write(json);
	}
}
