package br.com.infoway.cashmachine.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer number;
	private Integer verifyingDigit;

	@ManyToOne
	@JsonIgnore
	private Customer customer;

	@ManyToOne
	@JsonIgnore
	private Agency agency;

	private BigDecimal balance;
	private BigDecimal maximumLimit;
	private BigDecimal currentLimit;
	private BigDecimal fee;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Movement> movements;

	public Account() {
		this.movements = new ArrayList<>();
	}

	public Account(Agency agency, BigDecimal balance, BigDecimal limit, Customer customer, BigDecimal fee,
			Integer number, Integer digit) {
		this();
		this.agency = agency;
		this.balance = balance;
		this.maximumLimit = limit;
		this.currentLimit = limit;
		this.customer = customer;
		this.fee = fee;
		this.number = number;
		this.verifyingDigit = digit;
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

		BigDecimal realWithdrawal = value.add(fee);

		if (value.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("The value has to be greater than zero.");
		}

		if (realWithdrawal.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("The value has to be greater than zero.");
		}

		if (realWithdrawal.compareTo(balance) <= 0) {
			this.balance = this.balance.subtract(realWithdrawal);
			this.movements.add(new Movement("Successful withdrawal of " + value.toPlainString() + " + fee of " + fee.toPlainString(), this));
			return;
		}

		if (realWithdrawal.compareTo(balance.add(currentLimit)) > 0) {
			throw new IllegalArgumentException(
					"Your withdrawal cannot be higher than your current limit plus your balance.");
		}

		BigDecimal removeFromLimit = this.balance.subtract(realWithdrawal);
		this.currentLimit = this.currentLimit.add(removeFromLimit);
		this.balance = BigDecimal.ZERO;
		this.movements.add(new Movement("Successful withdrawal of " + value.toPlainString() + " + fee of " + fee.toPlainString(), this));

	}

	public void deposit(BigDecimal value) throws IllegalArgumentException {

		// valor <= 0;
		if (value.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("You cannot deposit a negative amount.");
		}

		this.balance = this.balance.add(value);
		this.movements.add(new Movement("Successful deposit of " + value.toPlainString(), this));

	}

	public void transfer(BigDecimal value, Account account) {

		this.withdrawal(value);
		account.deposit(value);

	}

	public BigDecimal getBalance() {
		return balance;
	}

}
