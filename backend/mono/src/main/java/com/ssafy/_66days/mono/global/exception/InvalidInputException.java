package com.ssafy._66days.mono.global.exception;

public class InvalidInputException extends RuntimeException {
	private static final long serialVersionUID = 234234321L;
	
	public InvalidInputException() {
		
	}
	
	public InvalidInputException(String msg) {
		super(msg);
	}
}
