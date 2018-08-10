package org.mindtree.practice.trainingprog.Exceptions;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class AllCustomException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IdAlreadyPresentException.class)
	public ResponseEntity<ErrorDetails> handleIdAlreadyPresentException(IdAlreadyPresentException exception, WebRequest request){
		System.out.println("handleIdAlreadyPresentException sysout is ");
		return new ResponseEntity<ErrorDetails>(new ErrorDetails(exception.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleIdNotFoundException(IdNotFoundException exception, WebRequest request){
		System.out.println("handleIdNotFoundException sysout is ");
		return new ResponseEntity<ErrorDetails>(new ErrorDetails(exception.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(IdInvalidException.class)
//	public ResponseEntity<ErrorDetails> handleIdInvalidException(IdInvalidException exception, WebRequest request){
//		return new ResponseEntity<ErrorDetails>(new ErrorDetails(exception.getMessage()), HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(NumberFormatException.class)
//	public ResponseEntity<ErrorDetails> handleNumberFormatException(OtherException exception, WebRequest request){
//		return new ResponseEntity<ErrorDetails>(new ErrorDetails(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@ExceptionHandler(OtherException.class)
	public ResponseEntity<ErrorDetails> handleException(OtherException exception, WebRequest request){
		return new ResponseEntity<ErrorDetails>(new ErrorDetails(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
