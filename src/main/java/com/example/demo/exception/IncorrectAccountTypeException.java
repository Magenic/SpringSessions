package com.example.demo.exception;

public class IncorrectAccountTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2866518466413901352L;

	
	public IncorrectAccountTypeException(String message) {
		super(message);
	}
	
	public IncorrectAccountTypeException(String message, Throwable t) {
		super(message, t);
	}
}
