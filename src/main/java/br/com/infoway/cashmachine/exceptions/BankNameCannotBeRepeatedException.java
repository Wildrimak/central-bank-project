package br.com.infoway.cashmachine.exceptions;

public class BankNameCannotBeRepeatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BankNameCannotBeRepeatedException(String message) {
		super(message);
	}

}
