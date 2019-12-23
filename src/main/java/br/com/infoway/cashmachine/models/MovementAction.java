package br.com.infoway.cashmachine.models;

public enum MovementAction {

	CREATED_ACCOUNT("Account Created"), WITHDREW("Withdrawal Realized"), SPECIAL_WITHDREW("Sprecial Withdrew Realized"),
	DEPOSITED("Deposit Realized"), TRANSFERRED("Transfer Realized"), TRANSFER_RECEIVED("transfer received");

	private String message;

	private MovementAction(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
