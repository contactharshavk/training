package org.mindtree.practice.trainingprog.Exceptions;

import java.math.BigInteger;

public class ErrorDetails {
	
	private String errorMessage;
	
	public ErrorDetails() {
		super();
	}

	public ErrorDetails(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
