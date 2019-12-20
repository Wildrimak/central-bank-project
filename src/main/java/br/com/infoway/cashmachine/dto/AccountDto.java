package br.com.infoway.cashmachine.dto;

import java.math.BigDecimal;

import br.com.infoway.cashmachine.models.Account;
import br.com.infoway.cashmachine.models.Agency;
import br.com.infoway.cashmachine.models.Customer;

public class AccountDto {

	private Long agency_id;
	private BigDecimal balance; // saldo
	private BigDecimal maximumLimit;
	private BigDecimal fee; // taxa
	private Integer number;
	private Integer verifyingDigit;

	public Long getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(Long agency_id) {
		this.agency_id = agency_id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getMaximumLimit() {
		return maximumLimit;
	}

	public void setMaximumLimit(BigDecimal maximumLimit) {
		this.maximumLimit = maximumLimit;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getVerifyingDigit() {
		return verifyingDigit;
	}

	public void setVerifyingDigit(Integer verifyingDigit) {
		this.verifyingDigit = verifyingDigit;
	}

	public Account getAccount(Customer customer) {

		Agency agency = new Agency();
		agency.setId(agency_id);
		Account account = new Account(agency, balance, maximumLimit, customer, fee, number, verifyingDigit);

		return account;
	}

}
