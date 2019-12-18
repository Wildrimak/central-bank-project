package br.com.infoway.cashmachine.models;

import java.math.BigDecimal;

public interface Account extends Movable {

	public Integer getNumber();

	public Integer getVerifyingDigit();

	public Customer getCostumer();

	public Agency getAgency();

	public BigDecimal getBalance();

}
