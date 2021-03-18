package com.zmc.parentmodule.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zmc.parentmodule.dto.APIError;

@RestController
@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler{

	@ExceptionHandler({ModuleNotFoundException.class,EmptyResultDataAccessException.class})
	public ResponseEntity<APIError> customExceptionHandler(Exception ex,ServletWebRequest webRequest){
		APIError apiError=new APIError();
		apiError.setTimeStamp(LocalDateTime.now());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setErrorMessages(Arrays.asList(ex.getMessage()));
		apiError.setPath(webRequest.getDescription(false).substring(4));
		return new ResponseEntity<APIError>(apiError,HttpStatus.valueOf(400));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> errors= ex.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
		APIError apiError=new APIError();
		apiError.setTimeStamp(LocalDateTime.now());
		apiError.setStatus(status);
		apiError.setErrorMessages(errors);
		apiError.setPath(request.getDescription(false).substring(4));
		return new ResponseEntity<Object>(apiError,status);
	}


}
