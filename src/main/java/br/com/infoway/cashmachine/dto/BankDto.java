package br.com.infoway.cashmachine.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.infoway.cashmachine.models.Bank;

public class BankDto {

	@NotBlank
	@Size(min = 2, max = 255)
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
