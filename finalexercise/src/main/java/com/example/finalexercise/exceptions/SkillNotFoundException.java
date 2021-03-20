package com.example.finalexercise.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SkillNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public SkillNotFoundException(String message) {
		super(message);
	}
}
