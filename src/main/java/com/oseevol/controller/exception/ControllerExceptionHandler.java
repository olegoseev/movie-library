package com.oseevol.controller.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oseevol.util.ResponseWrapper;


@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		return ResponseWrapper.notFound(ex, request);
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	protected ResponseEntity<Object> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
		return ResponseWrapper.badRequest(ex, request);
	}
	
	@ExceptionHandler(value = ResourceAlreadyExistsException.class) 
	protected ResponseEntity<Object> handleResourceExists(ResourceAlreadyExistsException ex, HttpServletRequest request) {
		return ResponseWrapper.conflict(ex, request);
	}
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
	    Map<String, String> errors = new HashMap<>();
	 
	    ex.getBindingResult().getFieldErrors().forEach(error -> 
	        errors.put(error.getField(), error.getDefaultMessage()));
	    return ResponseWrapper.argumentNotValid(ex);
	}
}
