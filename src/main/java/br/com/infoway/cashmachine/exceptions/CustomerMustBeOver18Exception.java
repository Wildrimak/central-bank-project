package br.com.infoway.cashmachine.exceptions;

public class CustomerMustBeOver18Exception extends IllegalArgumentException{

	private static final long serialVersionUID = 3314670270229233472L;
	
	@Override
	public String getMessage() {
		return "The customer must be over 18";
	}

}
