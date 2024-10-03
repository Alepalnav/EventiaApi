package com.jacaranda.eventia.exception;

public class ExceptionDuplicatedEmail extends RuntimeException{
	
	private static final long serialVersionUID = -409743470779314218L;
	
	public ExceptionDuplicatedEmail(String  exception) {
		super(exception);
	}

}
