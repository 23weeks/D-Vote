package com.dvote.backend.Exception;

public class CustomException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5593114529857434402L;
	private final int status;
	
	public CustomException(String message, int status) {
		super(message);
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
}
