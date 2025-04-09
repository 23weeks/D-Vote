package com.dvote.backend.config;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomAuthorityUtils {
	public static List<SimpleGrantedAuthority> getAdminAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	public static List<SimpleGrantedAuthority> getUserAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
}
