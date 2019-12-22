package br.com.infoway.cashmachine.dto;

import br.com.infoway.cashmachine.models.Agency;
import br.com.infoway.cashmachine.models.Bank;

public class AgencyDto {

	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Agency getAgency(Bank bank) {

		Agency agency = new Agency(number, bank);
		return agency;

	}

}
