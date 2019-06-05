package com.weather.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class Response {
	HttpStatus statusCode;
	String message;

	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "{statusCode : "+getStatusCode()+"}, message : "+getMessage()+"";
	}
}
