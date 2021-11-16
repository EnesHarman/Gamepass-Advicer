package com.gamepass.advicer.core.utilities.result.concretes;

import com.gamepass.advicer.core.utilities.result.abstracts.Result;

public class SuccessResult extends Result {

	public SuccessResult() {
		super(true);
	}
	
	public SuccessResult(String message) {
		super(true, message);
	}

}
