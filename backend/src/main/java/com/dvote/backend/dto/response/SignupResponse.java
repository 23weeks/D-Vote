package com.dvote.backend.dto.response;

public class SignupResponse {
	
	private String username;
	private String password;
	private boolean isAdmin;
	
	public SignupResponse(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
}
