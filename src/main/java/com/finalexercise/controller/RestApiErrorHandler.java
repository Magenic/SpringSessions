package com.finalexercise.controller;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.finalexercise.response.ApiError;


@RestControllerAdvice
public class RestApiErrorHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(Exception ex){
		ApiError apiError = new ApiError();
		apiError.setHttpStatus(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		apiError.setLocalDateTime(LocalDateTime.now());
		return buildResponse(apiError);
		
	}
	
	private ResponseEntity<Object> buildResponse(ApiError apiError) {
		return new ResponseEntity<Object>(apiError,apiError.getHttpStatus());
	}
	
}
