package com.oseevol.controller.exception;

import java.util.Map;

@SuppressWarnings("serial")
public class ResourceAlreadyExistsException extends BaseException{

	public ResourceAlreadyExistsException(String msg) {
		super(msg);
	}

	public ResourceAlreadyExistsException(String msg,  Map<String, String> details) {
		super(msg, details);
	}
}
