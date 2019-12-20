package br.com.infoway.cashmachine.dto;

import br.com.infoway.cashmachine.models.Agency;
import br.com.infoway.cashmachine.models.Bank;

public class AgencyDto {

	private Integer number;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Agency getAgency(Bank bank) {

		Agency agency = new Agency(number, bank);
		return agency;

	}

}
