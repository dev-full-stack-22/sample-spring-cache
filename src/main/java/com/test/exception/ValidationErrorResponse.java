package com.test.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

public class ValidationErrorResponse {

	private final String message;
	private List<FieldError> fieldErrors = new ArrayList<>();

	ValidationErrorResponse(String message) {
		this.message = message;
	}

	ValidationErrorResponse(String message, List<FieldError> fieldErrors) {
		this.message = message;
		this.fieldErrors = fieldErrors;
	}

	public String getMessage() {
		return message;
	}

	public void addFieldError(String objectName, String path, String message) {
		FieldError error = new FieldError(objectName, path, message);
		fieldErrors.add(error);
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

}
