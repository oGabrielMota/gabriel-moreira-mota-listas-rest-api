package br.com.aceleragep.GabrielListaRestAPI.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.aceleragep.GabrielListaRestAPI.exeptions.BadRequestBussinessException;
import br.com.aceleragep.GabrielListaRestAPI.exeptions.NotFoundBussinessException;
import br.com.aceleragep.GabrielListaRestAPI.exeptions.UnauthorizedAccessBussinessException;



@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadRequestBussinessException.class)
	public final ResponseEntity<ProblemExceptionOutput> handlerBadRequestBussinessException(
			BadRequestBussinessException ex, WebRequest request) {
		ProblemExceptionOutput problema = new ProblemExceptionOutput(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<ProblemExceptionOutput>(problema, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundBussinessException.class)
	public final ResponseEntity<ProblemExceptionOutput> handlerNotFoundBussinessException(NotFoundBussinessException ex,
			WebRequest request) {
		ProblemExceptionOutput problema = new ProblemExceptionOutput(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<ProblemExceptionOutput>(problema, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedAccessBussinessException.class)
	public final ResponseEntity<ProblemExceptionOutput> handlerNotFoundBussinessException(
			UnauthorizedAccessBussinessException ex, WebRequest request) {
		ProblemExceptionOutput problema = new ProblemExceptionOutput(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
		return new ResponseEntity<ProblemExceptionOutput>(problema, HttpStatus.UNAUTHORIZED);
	}


}
