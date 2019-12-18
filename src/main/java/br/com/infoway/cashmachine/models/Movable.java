package br.com.infoway.cashmachine.models;

import java.math.BigDecimal;

public interface Movable {

	public void withdrawal(BigDecimal value);

	public void deposit(BigDecimal value);

	public void transfer(BigDecimal value, Account account);

}
