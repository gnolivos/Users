package com.gnolivos.users.vo;

public class GlobalResponse {
	
	private String message;
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
