package com.gamepass.advicer.core.utilities.result.concretes;

import com.gamepass.advicer.core.utilities.result.abstracts.Result;

public class ErrorResult  extends Result{

	public ErrorResult() {
		super(false);
	}
	
	public ErrorResult(String message) {
		super(false, message);
	}
	
}
