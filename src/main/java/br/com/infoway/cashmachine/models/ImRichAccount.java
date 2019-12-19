package br.com.infoway.cashmachine.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ImRichAccount implements Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer number;
	private Integer verifyingDigit;
	@ManyToOne
	private Customer customer;
	private Agency agency;
	private BigDecimal balance;

	public ImRichAccount() {
	}

	public Long getId() {
		return id;
	}

	@Override
	public Integer getNumber() {
		return number;
	}

	@Override
	public Integer getVerifyingDigit() {
		return verifyingDigit;
	}

	@Override
	public Customer getCostumer() {
		return customer;
	}

	@Override
	public Agency getAgency() {
		return agency;
	}

	@Override
	public void withdrawal(BigDecimal value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deposit(BigDecimal value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transfer(BigDecimal value, Account account) {
		// TODO Auto-generated method stub

	}

	@Override
	public BigDecimal getBalance() {
		return balance;
	}

}
