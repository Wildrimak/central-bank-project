package br.com.infoway.cashmachine.models;

import java.math.BigDecimal;

public class CurrentAccount implements Account {

	private Long id;
	private Integer number;
	private Integer verifyingDigit;
	private Customer customer;
	private Agency agency;
	private BigDecimal balance; // saldo
	private BigDecimal limit;
	private BigDecimal currentLimit;
	private BigDecimal fee; // taxa

	public CurrentAccount() {
	}

	public Long getId() {
		return id;
	}

	@Override
	public Integer getNumber() {
		return number;
	}

	public BigDecimal getCurrentLimit() {
		return currentLimit;
	}

	@Override
	public Integer getVerifyingDigit() {
		return verifyingDigit;
	}

	@Override
	public Customer getCostumer() {
		return customer;
	}

	public BigDecimal getFee() {
		return fee;
	}

	@Override
	public Agency getAgency() {
		return agency;
	}

	public BigDecimal getLimit() {
		return limit;
	}

	@Override
	public void withdrawal(BigDecimal value) {

		// saqueReal = valor + taxa
		BigDecimal realWithdrawal = value.add(fee);

		// valor <= 0;
		if (value.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("The value has to be greater than zero.");
		}

		// saqueReal <= 0;
		if (realWithdrawal.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("The value has to be greater than zero.");
		}

		// saqueReal <= balance
		if (realWithdrawal.compareTo(balance) <= 0) {
			this.balance = this.balance.subtract(realWithdrawal);
			return;
		}

		// saqueReal > saldo + limite
		if (realWithdrawal.compareTo(balance.add(currentLimit)) > 0) {
			throw new IllegalArgumentException(
					"Your withdrawal cannot be higher than your current limit plus your balance.");
		}

		// retireDoLimite = saldo - valor
		// limiteCorrente = limiteCorrente + retireDoLimite
		// this.saldo = 0;
		BigDecimal removeFromLimit = this.balance.subtract(realWithdrawal);
		this.currentLimit = this.currentLimit.add(removeFromLimit);
		this.balance = BigDecimal.ZERO;
	}

	@Override
	public void deposit(BigDecimal value) {

		// valor <= 0;
		if (value.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("You cannot deposit a negative amount.");
		}

		this.balance = this.balance.add(value);

	}

	@Override
	public void transfer(BigDecimal value, Account account) {

		this.withdrawal(value);
		account.deposit(value);

	}

	@Override
	public BigDecimal getBalance() {
		return balance;
	}

}
