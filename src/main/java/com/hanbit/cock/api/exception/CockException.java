package com.hanbit.cock.api.exception;


public class CockException extends RuntimeException {
	
	private int errorCode = 500;
	
	public CockException(String message) {
		// 여기서 super는 Runtime Exceptiont이다.
		super(message);
	}
	
	public CockException(int errorCode, String message) {
		this(message);

		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
