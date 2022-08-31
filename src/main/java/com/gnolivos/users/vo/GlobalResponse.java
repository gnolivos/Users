package com.gnolivos.users.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class GlobalResponse {
	
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private UserResponse userResponse;
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the userResponse
	 */
	public UserResponse getUserResponse() {
		return userResponse;
	}
	/**
	 * @param userResponse the userResponse to set
	 */
	public void setUserResponse(UserResponse userResponse) {
		this.userResponse = userResponse;
	}
	
}
