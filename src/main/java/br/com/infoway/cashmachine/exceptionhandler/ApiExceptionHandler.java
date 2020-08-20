package br.com.infoway.cashmachine.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.infoway.cashmachine.exceptionhandler.Error.Field;
import br.com.infoway.cashmachine.exceptions.BankNameCannotBeRepeatedException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(BankNameCannotBeRepeatedException.class)
	public ResponseEntity<Object> handleBankNameCannotBeRepeatedException(BankNameCannotBeRepeatedException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		Error error = new Error(status.value(), ex.getMessage());

		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error.Field> fields = new ArrayList<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {

			String entity = error.getObjectName();
			String field = ((FieldError) error).getField();
			String motive = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			fields.add(new Field(entity, field, motive));

		});

		String message = "Error: any field is invalid, please try again!";
		Error error = new Error(status.value(), message);
		error.setFields(fields);

		return super.handleExceptionInternal(ex, error, headers, status, request);
	}

}
