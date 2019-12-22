package br.com.infoway.cashmachine.exceptions;

public class EmailCannotBeTheSameException extends IllegalArgumentException {

	private static final long serialVersionUID = -625703590905402704L;

	@Override
	public String getMessage() {
		return "The email can not be the same";
	}
	
}
