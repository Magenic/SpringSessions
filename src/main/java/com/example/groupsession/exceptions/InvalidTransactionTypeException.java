package com.example.groupsession.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidTransactionTypeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidTransactionTypeException(String message) {
		super(message);
	}
}
