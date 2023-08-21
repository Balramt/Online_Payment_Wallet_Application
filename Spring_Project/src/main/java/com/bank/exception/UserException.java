package com.bank.exception;

public class UserException extends RuntimeException {

	public UserException(String str) {
		super(str);
	}

	public UserException() {
		super();
	}

	public UserException(Exception e, String msg) {
		super(msg, e);
	}
}
