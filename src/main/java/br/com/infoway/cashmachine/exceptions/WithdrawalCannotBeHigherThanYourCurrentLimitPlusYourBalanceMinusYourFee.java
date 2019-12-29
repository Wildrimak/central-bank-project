package br.com.infoway.cashmachine.exceptions;

public class WithdrawalCannotBeHigherThanYourCurrentLimitPlusYourBalanceMinusYourFee extends IllegalArgumentException {

	private static final long serialVersionUID = 6272435495786764945L;
	
	@Override
	public String getMessage() {
		return "Your withdrawal cannot be higher than your current limit plus your balance minus your fee.";
	}

}
