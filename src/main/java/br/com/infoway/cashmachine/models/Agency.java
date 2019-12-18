package br.com.infoway.cashmachine.models;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Agency {

	private Long id;
	private Integer number;
	private Date creationDate;
	private Bank bank;
	private List<Account> accounts;

	public Agency() {
	}

	public Long getId() {
		return id;
	}

	public Integer getNumber() {
		return number;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Bank getBank() {
		return bank;
	}

	public List<Account> getAccounts() {
		return Collections.unmodifiableList(accounts);
	}

}
