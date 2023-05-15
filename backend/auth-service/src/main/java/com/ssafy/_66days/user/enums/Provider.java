package com.ssafy._66days.user.enums;

public enum Provider {
	KAKAO("KAKAO");

	private final String name;

	Provider(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
