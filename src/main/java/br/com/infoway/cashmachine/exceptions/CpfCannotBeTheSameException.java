package br.com.infoway.cashmachine.exceptions;

public class CpfCannotBeTheSameException extends IllegalArgumentException {

	private static final long serialVersionUID = 1576198102904504999L;

	@Override
	public String getMessage() {
		return "The cpf can not be the same";
	}

}
