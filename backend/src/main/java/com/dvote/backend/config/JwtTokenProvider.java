package com.dvote.backend.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenProvider {
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	private SecretKey key;
	
	private final long tokenValidityMilliseconds = 30 * 60 * 1000L; //30분
	
	@PostConstruct
	protected void init() {
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	//토큰 생성
	public String createToken(String username, boolean isAdmin) {
		long now = System.currentTimeMillis();
		Date issuedAt = new Date(now);
		Date expiry = new Date(now + tokenValidityMilliseconds);
		
		return Jwts.builder()
				.subject(username)
				.claim("ADMIN", isAdmin)
				.issuedAt(issuedAt)
				.expiration(expiry)
				.signWith(key, Jwts.SIG.HS256)
				.compact();
	}
	
	//토큰에서 username 추출
	public String getUsername(String token) {
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	//토큰에서 isAdmin 추출
	public boolean isAdmin(String token) {
		return Boolean.TRUE.equals(
				Jwts.parser()
						.verifyWith(key)
						.build()
						.parseSignedClaims(token)
						.getPayload()
						.get("ADMIN", Boolean.class)
		);
	}
	
	//토큰 유효성 검증
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
					.verifyWith(key)
					.build()
					.parseSignedClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
