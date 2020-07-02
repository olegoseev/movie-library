package com.oseevol.controller.exception;

import java.util.Map;

import org.springframework.lang.Nullable;

@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException {
	
	private final Map<String, String> details;

	public BaseException(String msg) {
		this(msg, null); 
	}
	
	public BaseException(String msg, @Nullable Map<String, String> details) {
		super(msg);
		this.details = details;
	}

	public Map<String, String> getDetails() {
		return details;
	}
}
