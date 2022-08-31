package com.gnolivos.users.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UserValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserValidationException(String message) {
		super(message);
	}
	
	public UserValidationException(String userId, String message) {
		super("Could not find user with id = " + userId + ", Message = " +message);
	}
	
}
