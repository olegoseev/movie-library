package com.oseevol.util;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public final class MessagesMapper {
	
	private static final String TIMESTAMP = "timestamp";
	private static final String STATUS = "status";
	private static final String ERROR = "error";
	private static final String DATA = "data";
	private static final String PATH = "path";
	private static final String MESSAGE = "message";
	private static final String DETAILS = "details";
	
	
	private MessagesMapper() {}
	
	/**
	 * Resource not found response body
	 * 
	 * @return map
	 */
	public static Map<String, Object> notFound(String url, String msg, @Nullable Map<String, String> details) {

		Map<String, Object> map = new LinkedHashMap<>();

		map.put(TIMESTAMP, LocalDateTime.now());
		map.put(STATUS,  HttpStatus.NOT_FOUND.value());
		map.put(ERROR, HttpStatus.NOT_FOUND.getReasonPhrase());
		map.put(MESSAGE, msg);
		if(details != null) {
			map.put(DETAILS, details);
		}
		map.put(PATH, url);
		return map;
	}
	
	/**
	 * Success
	 * 
	 * @param obj
	 * @return map
	 */
	public static Map<String, Object> success(Object obj) {
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(TIMESTAMP, LocalDateTime.now());
		map.put(STATUS, HttpStatus.OK);
		map.put(DATA, obj);
		
		return map;
	}
	
	public static Map<String, Object> created(Object obj) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(TIMESTAMP, LocalDateTime.now());
		map.put(STATUS, HttpStatus.CREATED);
		
		Map<String, Object> data = new LinkedHashMap<>();
		data.put(PATH, obj);
		map.put(DATA, data);
		return map;
	}

	/**
	 * Bad request response body
	 * @param url to resource that generated bad request
	 * @param msg message
	 * @return
	 */
	public static Object badRequest(@Nullable String url, String msg, @Nullable Map<String, String> details) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(TIMESTAMP, LocalDateTime.now());
		map.put(STATUS, HttpStatus.BAD_REQUEST.value());
		map.put(ERROR, HttpStatus.BAD_REQUEST.getReasonPhrase());
		map.put(MESSAGE, msg);
		if(details != null) {
			map.put(DETAILS, details);
		}
		if(url != null) {
			map.put(PATH, url);
		}
		return map;
	}
	
	
	/**
	 * Bad request response body
	 * @param msg message
	 * @return
	 */
	public static Object badRequest(String msg, @Nullable Map<String, String> details) {
		return badRequest(null, msg, details);
	}
	
	/**
	 * Conflict request response body
	 * @param url to resource that generated bad request
	 * @param msg message
	 * @return
	 */
	public static Object conflict(String url, String msg, @Nullable Map<String, String> details) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(TIMESTAMP, LocalDateTime.now());
		map.put(STATUS, HttpStatus.CONFLICT.value());
		map.put(ERROR, HttpStatus.CONFLICT.getReasonPhrase());
		map.put(MESSAGE, msg);
		if(details != null) {
			map.put(DETAILS, details);
		}
		map.put(PATH, url);
		return map;
	}
	
	
	/**
	 * General error
	 * 
	 * @param message
	 * @return map
	 */
	public static Map<String, Object> appError(String message) {

		Map<String, Object> map = new LinkedHashMap<>();

		map.put(STATUS, "error");
		map.put("message", message);

		return map;
	}

	/**
	 * Internal server error
	 * 
	 * @return map
	 */
	public static Map<String, Object> internalServerError() {

		Map<String, Object> map = new LinkedHashMap<>();

		map.put("status", "error");
		map.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
		map.put("message", "Internal Server Error");

		return map;
	}



}
