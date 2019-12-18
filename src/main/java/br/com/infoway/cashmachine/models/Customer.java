package br.com.infoway.cashmachine.models;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Customer {

	private Long id;
	private String fullName;
	private String cpf;
	private Login login;
	private Date birthDate;
	private Date registrationDate;
	private List<Account> accounts;
	
	public Customer() {
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getCpf() {
		return cpf;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public List<Account> getAccounts() {
		return Collections.unmodifiableList(accounts);
	}
	
	
}
