package br.com.infoway.cashmachine.models;

public class SavingsAccount implements Account {

	private Long id;
	private Integer number;
	private Integer verifyingDigit;
	private Customer customer;
	private Agency agency;

	public SavingsAccount() {
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

}
