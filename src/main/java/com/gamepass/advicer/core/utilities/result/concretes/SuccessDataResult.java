package com.gamepass.advicer.core.utilities.result.concretes;

import com.gamepass.advicer.core.utilities.result.abstracts.DataResult;

public class SuccessDataResult<T> extends DataResult<T>{
	
	public SuccessDataResult() {
		super(true);
	}

	public SuccessDataResult( String message) {
		super(true,message);
	}
	
	public SuccessDataResult( String message, T data) {
		super(true, message, data);
	}
}
