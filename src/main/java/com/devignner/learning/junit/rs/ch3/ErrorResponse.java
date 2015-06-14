package com.devignner.learning.junit.rs.ch3;

public class ErrorResponse implements Response{

	private Request originalRequest;
	private Exception originalException;
	
	public ErrorResponse(Request request, Exception exception){
		this.originalRequest = request; 
		this.originalException = exception;
	}

	
	public Request getOriginalRequest() {
		return originalRequest;
	}

	public Exception getOriginalException() {
		return originalException;
	}


	public String getName() {
		return null;
	}
	
	
}
