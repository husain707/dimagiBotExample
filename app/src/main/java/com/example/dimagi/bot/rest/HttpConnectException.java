package com.example.dimagi.bot.rest;

public class HttpConnectException extends Exception {

	public static final String MSG_NO_INTERNET = "No internet access";
	public static final String MSG_SESSION_EXPIRED = "session expired";

	private static final long serialVersionUID = 1L;

	private int statusCode;

	public HttpConnectException(String message) {
		super(message);
	}

	public HttpConnectException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}