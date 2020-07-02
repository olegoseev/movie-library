package com.oseevol.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.oseevol.controller.exception.BadRequestException;
import com.oseevol.controller.exception.ResourceAlreadyExistsException;
import com.oseevol.controller.exception.ResourceNotFoundException;

public class ResponseWrapper {
	
	private static final String ARGUMENT_NOT_VALID = "Argument not valid or missing";
	
	private ResponseWrapper() {}

	public static ResponseEntity<Object> ok (Object obj){
		return ResponseEntity.ok(MessagesMapper.success(obj));
	}
	
	/**
	 * Return {@code ResponseEntity} with HTTP 404 Not found status code, body and no headers.
	 * @param message
	 * @return
	 */
	public static ResponseEntity<Object> notFound(ResourceNotFoundException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessagesMapper.notFound(request.getRequestURI(), ex.getMessage(), ex.getDetails()));
	}

	/**
	 * Return {@code ResponseEntity} with HTTP 201 Created status code, body and no headers.
	 * @param uri resource location
	 * @return Response entity
	 */
	public static ResponseEntity<Object> created(String path) {
		return ResponseEntity.created(URI.create(path)).body(MessagesMapper.created(path));
	}

	/**
	 * Return {@code ResponseEntity} with HTTP 401 Not found status code, body and no headers.
	 * @param msg error message
	 * @return Response entity
	 */
	public static ResponseEntity<Object> badRequest(BadRequestException ex, HttpServletRequest request) {
		return ResponseEntity.badRequest().body(MessagesMapper.badRequest(request.getRequestURI(), ex.getMessage(), ex.getDetails()));
	}
	
	public static ResponseEntity<Object> noContent() {
		return ResponseEntity.noContent().build();
	}

	
	public static ResponseEntity<Object> conflict(ResourceAlreadyExistsException ex, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(MessagesMapper.conflict(request.getRequestURI(), ex.getMessage(), ex.getDetails()));
	}

	public static ResponseEntity<Object> argumentNotValid(MethodArgumentNotValidException ex) {
		
	    Map<String, String> details = new HashMap<>();
		 
	    ex.getBindingResult().getFieldErrors().forEach(error -> 
	    details.put(error.getField(), error.getDefaultMessage()));
	    return ResponseEntity.badRequest().body(MessagesMapper.badRequest(ARGUMENT_NOT_VALID, details));
	}
}
