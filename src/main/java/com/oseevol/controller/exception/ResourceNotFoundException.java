package com.oseevol.controller.exception;

import java.util.Map;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends BaseException {
	
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
    
    public ResourceNotFoundException(String msg, Map<String, String> details ) {
        super(msg, details);
    }
}
