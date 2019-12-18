package br.com.infoway.cashmachine.models;

public interface Account {
	
	public Integer getNumber();
	public Integer getVerifyingDigit();
	public Customer getCostumer();
	public Agency getAgency();

}
