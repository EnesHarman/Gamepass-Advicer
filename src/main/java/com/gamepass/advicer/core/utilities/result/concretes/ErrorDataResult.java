package com.gamepass.advicer.core.utilities.result.concretes;

import com.gamepass.advicer.core.utilities.result.abstracts.DataResult;

public class ErrorDataResult<T> extends DataResult<T> {

	public ErrorDataResult() {
		super(false);
	}

	public ErrorDataResult( String message) {
		super(false,message);
	}
	
	public ErrorDataResult( String message, T data) {
		super(false, message, data);
	
	}
}
