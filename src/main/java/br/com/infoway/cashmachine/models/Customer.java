package br.com.infoway.cashmachine.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	private String cpf;
	private Login login;
	private Date birthDate;
	private Date registrationDate;
	
	@OneToMany(mappedBy = "customer")
	private List<Account> accounts;
	
	public Customer() {
		this.registrationDate = new Date();
		this.accounts = new ArrayList<>();
	}
	
	public Customer(String fullName, String cpf) {
		this();
		this.fullName = fullName;
		this.cpf = cpf;
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
