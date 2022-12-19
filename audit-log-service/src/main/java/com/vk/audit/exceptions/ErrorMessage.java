package com.vk.audit.exceptions;

public class ErrorMessage {
	private int statusCode;
	private String message;
	private String description;

	public ErrorMessage(int statusCode, String message, String description) {
		this.statusCode = statusCode;
		this.message = message;
		this.description = description;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getDescription() {
		return description;
	}

	public String getMessage() {
		return message;
	}
}