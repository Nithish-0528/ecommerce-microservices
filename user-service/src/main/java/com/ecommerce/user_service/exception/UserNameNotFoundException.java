package com.ecommerce.user_service.exception;

public class UserNameNotFoundException extends RuntimeException {
	
	public UserNameNotFoundException(String message) {
		super(message);
	}
	
}