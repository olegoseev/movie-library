package com.oseevol.controller.exception;

import java.util.Map;

@SuppressWarnings("serial")
public class BadRequestException extends BaseException {
	public BadRequestException(String msg) {
		super(msg);
	}
	
	public BadRequestException(String msg, Map<String, String> details) {
		super(msg, details);
	}
}
