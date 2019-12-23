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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String number;

	@Range(min = 0, max = 9)
	private Integer verifyingDigit;

	@ManyToOne
	@JsonIgnore
	private Customer customer;

	@ManyToOne
	@JsonIgnore
	private Agency agency;

	@NotNull
	@Range(min = 0)
	private BigDecimal balance;

	@NotNull
	@Range(min = 0)
	private BigDecimal maximumLimit;

	@NotNull
	@Range(min = 0)
	private BigDecimal currentLimit;

	@NotNull
	@Range(min = 0)
	private BigDecimal fee;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Movement> movements;

	public Account() {
		this.movements = new ArrayList<>();
	}

	public Account(Agency agency, BigDecimal balance, BigDecimal limit, Customer customer, BigDecimal fee,
			String number, Integer digit) {
		this();
		this.agency = agency;
		this.balance = balance;
		this.maximumLimit = limit;
		this.currentLimit = limit;
		this.customer = customer;
		this.fee = fee;
		this.number = number;
		this.verifyingDigit = digit;
		this.notifyMovements(balance, MovementAction.CREATED_ACCOUNT);

	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
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

	public BigDecimal getBalance() {
		return balance;
	}

	public BigDecimal getMaximumLimit() {
		return maximumLimit;
	}

	public void withdrawal(BigDecimal value) throws IllegalArgumentException {

		validateWithdrawal(value);

		BigDecimal realWithdrawal = value.add(fee);

		if (realWithdrawal.compareTo(balance) <= 0) {
			withdrawalAlgorithm(realWithdrawal);
			notifyMovements(value, MovementAction.WITHDREW);
			return;
		}

		validateWithdrawalPlusLimit(realWithdrawal);

		specialWithdrawalAlgorithm(realWithdrawal);
		notifyMovements(value, MovementAction.SPECIAL_WITHDREW);

	}

	public void deposit(BigDecimal value) throws IllegalArgumentException {

		depositAlgorithm(value);
		this.movements.add(new Movement(MovementAction.DEPOSITED, this));

	}

	// transfer don't has fee
	public void transfer(BigDecimal value, Account account) {

		theValueHasToBeGreaterThanZero(value);

		if (value.compareTo(balance) <= 0) {
			this.withdrawalAlgorithm(value);
			account.depositAlgorithm(value);
			account.notifyMovements(value, MovementAction.TRANSFER_RECEIVED);
			this.movements.add(new Movement(MovementAction.TRANSFERRED, this));
		}

		throw new IllegalArgumentException("You do not have enough balance to transfer");
	}

	private void validateWithdrawal(BigDecimal value) {

		theValueHasToBeGreaterThanZero(value);
		valuePlusFeeMustBeGreaterThanZero(value);

	}

	private void validateWithdrawalPlusLimit(BigDecimal value) {
		withdrawalCannotBeHigherThanYourCurrentLimitPlusYourBalance(value);
	}

	private void withdrawalCannotBeHigherThanYourCurrentLimitPlusYourBalance(BigDecimal realWithdrawal)
			throws IllegalArgumentException {

		if (realWithdrawal.compareTo(balance.add(currentLimit)) > 0) {
			throw new IllegalArgumentException(
					"Your withdrawal cannot be higher than your current limit plus your balance.");
		}

	}

	private void valuePlusFeeMustBeGreaterThanZero(BigDecimal value) throws IllegalArgumentException {

		BigDecimal realWithdrawal = value.add(fee);

		if (realWithdrawal.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("The value plus fee must be greater than zero.");
		}

	}

	private void theValueHasToBeGreaterThanZero(BigDecimal value) throws IllegalArgumentException {

		if (value.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("The value has to be greater than zero.");
		}

	}

	private void specialWithdrawalAlgorithm(BigDecimal realWithdrawal) {
		BigDecimal removeFromLimit = this.balance.subtract(realWithdrawal);
		this.currentLimit = this.currentLimit.add(removeFromLimit);
		this.balance = BigDecimal.ZERO;
	}

	private void withdrawalAlgorithm(BigDecimal realWithdrawal) {
		this.balance = this.balance.subtract(realWithdrawal);
	}

	private void notifyMovements(BigDecimal value, MovementAction action) {
		this.movements.add(new Movement(action, this));
	}

	private void depositAlgorithm(BigDecimal value) {
		if (value.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("You cannot deposit a negative amount.");
		}

		this.balance = this.balance.add(value);
	}

}
