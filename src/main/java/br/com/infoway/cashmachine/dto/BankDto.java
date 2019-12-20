package br.com.infoway.cashmachine.dto;

import br.com.infoway.cashmachine.models.Bank;

public class BankDto {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bank getBank() {
		Bank bank = new Bank(this.name);
		return bank;
	}
}
