package com.school.platform.pojo;

public class InputError {
	
	private String inputFieldName;
	private String errorMessage;
	
	public InputError() {
		
	}
	
	public InputError(String inputFieldName, String errorMessage) {
		this.inputFieldName = inputFieldName;
		this.errorMessage = errorMessage;
	}

	public String getInputFieldName() {
		return inputFieldName;
	}

	public void setInputFieldName(String inputFieldName) {
		this.inputFieldName = inputFieldName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
