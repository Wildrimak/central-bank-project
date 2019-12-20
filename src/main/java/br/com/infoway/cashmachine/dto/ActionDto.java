package br.com.infoway.cashmachine.dto;

import java.math.BigDecimal;

public class ActionDto {
	
	private BigDecimal value;
	private Long idAccount;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}

}
