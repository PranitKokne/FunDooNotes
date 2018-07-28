package com.fundoonotes.read.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fundoonotes.read.model.ErrorDetails;
import com.fundoonotes.read.util.ResourceNotFoundException;

@ControllerAdvice
@PropertySource({ "classpath:exception.properties" })
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

	@Autowired
	private Environment env;

	/*@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAnyException(Exception ex, WebRequest webRequest) {
		LOGGER.error(env.getProperty("internal.server.error"));
		LOGGER.error(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest webRequest) {
		LOGGER.error(env.getProperty("resource.not.found.exception"));
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
