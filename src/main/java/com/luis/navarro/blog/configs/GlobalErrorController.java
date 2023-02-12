package com.luis.navarro.blog.configs;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.luis.navarro.blog.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalErrorController {

	// For UI Pages
	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundException(UserNotFoundException ex) {
		return "frontend/login";
	}

	// For REST APIs
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentException(IllegalArgumentException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex){
		log.info("SQLException Occured:: URL="+request.getRequestURL());
		return "database_error";
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		log.error("IOException handler executed");
		//returning 404 error code
	}
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal Server Error")
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest request, Exception ex){
		log.info("Internal Server Error Occured:: URL=" + request.getRequestURL());
		return "frontend/login";
	}
}