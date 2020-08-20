package br.com.infoway.cashmachine.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.infoway.cashmachine.exceptionhandler.Error.Field;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error.Field> fields = new ArrayList<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {

			String entity = error.getObjectName();
			String field = ((FieldError) error).getField();
			String motive = error.getDefaultMessage();

			fields.add(new Field(entity, field, motive));

		});

		String message = "Error: any field is invalid, please try again!";
		Error error = new Error(status.value(), message);
		error.setFields(fields);

		return super.handleExceptionInternal(ex, error, headers, status, request);
	}

}
