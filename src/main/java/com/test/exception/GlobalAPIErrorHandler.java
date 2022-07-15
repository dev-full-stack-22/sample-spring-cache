package com.test.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalAPIErrorHandler {

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse genericErrorresponse(Exception ex) {
		return new ErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(value = { RuntimeException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse genericErrorResponse(Exception ex) {
		return new ErrorResponse("Unexpected Error occurred");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationErrorResponse genericResponse(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(fieldErrors);
	}

	private ValidationErrorResponse processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
		log.debug("fieldErrors " + fieldErrors);
		ValidationErrorResponse error = new ValidationErrorResponse("validation error");
		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			error.addFieldError("", fieldError.getField(), fieldError.getDefaultMessage());
		}
		return error;
	}

}
