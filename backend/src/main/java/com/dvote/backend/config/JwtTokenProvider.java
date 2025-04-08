package com.dvote.backend.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	private Key key;
	
	private final long tokenValidityMilliseconds = 60 * 60 * 1000L; //1시간
	
	@PostConstruct
	protected void init() {
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String createToken(String username, String role) {
		Claims claims = (Claims) Jwts.claims().subject(username);
		claims.put("role", role);
		
		Date now = new Date();
		Date expiry = new Date(now.getTime() + tokenValidityMilliseconds);
		
		return Jwts.builder()
				.claims(claims)
				.issuedAt(now)
				.expiration(expiry)
				.signWith(key)
				.compact();
	}
	
	//토큰에서 사용자 이름 추출
	public String getUsername(String token) {
		return parseClaims(token).getBody().getSubject();
	}
	
	//토큰에서 권한(role) 추출
	public String getUserRole(String token) {
		return (String) parseClaims(token).getBody().get("role");
	}
	
	//토큰 유효성 검사
	public boolean validateToken(String token) {
		
		try {
			parseClaims(token);
			
			return true;
			
		} catch (JwtException | IllegalArgumentException e) {
			
			return false;
		}
	}
	
	//요청 헤더에서 토큰 추출
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}
	
	private Jws<Claims> parseClaims(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey) key)
				.build()
				.parseSignedClaims(token);
	}
}
