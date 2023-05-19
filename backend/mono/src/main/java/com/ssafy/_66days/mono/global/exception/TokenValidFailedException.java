package com.ssafy._66days.mono.global.exception;

public class TokenValidFailedException extends RuntimeException {
	private static final long serialVersionUID = 2238030302650813811L;
	
	public TokenValidFailedException(String msg) {
		super(msg);
	}
}