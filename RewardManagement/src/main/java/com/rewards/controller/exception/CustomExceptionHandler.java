package com.rewards.controller.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	/**
	 * Handle customer Not Found Excepotion's 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleException(CustomerNotFoundException ex) {
		Map<String, String> map = new HashMap<>();
		map.put("message", ex.getMessage());

		return map;

	}

	/**
	 * Handle all the other exception 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleException(Exception ex) {
		Map<String, String> map = new HashMap<>();
		map.put("message", ex.getMessage());
		return map;

	}

}
