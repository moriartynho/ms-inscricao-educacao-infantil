package com.moriartynho.msinscricaoeducacaoinfantil.infra.handler;

import java.io.IOException;
import java.time.Instant;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.moriartynho.msinscricaoeducacaoinfantil.exception.ClassificationException;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.EditValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;

@ControllerAdvice
public class ResolveExceptionHandler {

	@ExceptionHandler(ClassificationException.class)
	public ResponseEntity<ResponseMessageError> classificationExceptionHandler(ClassificationException exception,
			HttpServletRequest request) {
		return popularResponseMessageError(exception, HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request);
	}

	@ExceptionHandler(EditValidationException.class)
	public ResponseEntity<ResponseMessageError> editValidationExceptionHandler(EditValidationException exception,
			HttpServletRequest request) {
		return popularResponseMessageError(exception, HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseMessageError> validationExceptionHandler(ValidationException exception,
			HttpServletRequest request) {
		return popularResponseMessageError(exception, HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request);
	}

	@ExceptionHandler(RegisterValidationException.class)
	public ResponseEntity<ResponseMessageError> regsiterValidationExceptionHandler(RegisterValidationException exception,
			HttpServletRequest request) {
		return popularResponseMessageError(exception, HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ResponseMessageError> dataAcessExceptionHandler(DataAccessException exception,
			HttpServletRequest request) {
		return popularResponseMessageError(exception, HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request);
	}

	@ExceptionHandler(InternalErrorException.class)
	public ResponseEntity<ResponseMessageError> internalErrornExceptionHandler(InternalErrorException exception,
			HttpServletRequest request) {
		return popularResponseMessageError(exception, HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ResponseMessageError> ioExceptionExceptionHandler(IOException exception,
			HttpServletRequest request) {
		return popularResponseMessageError(exception, HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request);
	}
	
	@ExceptionHandler(InterruptedException.class)
	public ResponseEntity<ResponseMessageError> interruptedExceptionHandler(InterruptedException exception,
			HttpServletRequest request) {
		return popularResponseMessageError(exception, HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request);
	}

	private static ResponseEntity<ResponseMessageError> popularResponseMessageError(Exception e, Integer status,
			String titulo, HttpServletRequest request) {
		ResponseMessageError err = new ResponseMessageError();
		err.setTimestamp(Instant.now());
		err.setStatus(status);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
