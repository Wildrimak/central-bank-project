package br.com.infoway.cashmachine.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer number;
	private Integer verifyingDigit;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private Agency agency;

	private BigDecimal balance; // saldo
	private BigDecimal maximumLimit;
	private BigDecimal currentLimit;
	private BigDecimal fee; // taxa

	@OneToMany(mappedBy = "account")
	private List<Movement> movements;

	public Account() {
	}

	public Long getId() {
		return id;
	}

	public Integer getNumber() {
		return number;
	}

	public BigDecimal getCurrentLimit() {
		return currentLimit;
	}

	public Integer getVerifyingDigit() {
		return verifyingDigit;
	}

	public Customer getCustomer() {
		return customer;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public Agency getAgency() {
		return agency;
	}

	public BigDecimal getMaximumLimit() {
		return maximumLimit;
	}

	public void withdrawal(BigDecimal value) throws IllegalArgumentException {

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

	public void deposit(BigDecimal value) throws IllegalArgumentException {

		// valor <= 0;
		if (value.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("You cannot deposit a negative amount.");
		}

		this.balance = this.balance.add(value);

	}

	public void transfer(BigDecimal value, Account account) {

		this.withdrawal(value);
		account.deposit(value);

	}

	public BigDecimal getBalance() {
		return balance;
	}

}
