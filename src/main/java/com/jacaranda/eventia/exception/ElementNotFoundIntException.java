package com.jacaranda.eventia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundIntException extends RuntimeException {
	
	public ElementNotFoundIntException(Integer id) {

		super("No se puede encontrar el elemento con id=" + id);
		}

}
