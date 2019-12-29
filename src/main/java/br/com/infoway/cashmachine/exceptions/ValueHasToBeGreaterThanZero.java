package br.com.infoway.cashmachine.exceptions;

public class ValueHasToBeGreaterThanZero extends IllegalArgumentException {

	private static final long serialVersionUID = -7329629938297123798L;

	@Override
	public String getMessage() {
		return "The value has to be greater than zero.";
	}
	
}
