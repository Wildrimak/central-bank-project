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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullName;
	private String cpf;
	private String email;

	@JsonIgnore
	private String password;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public List<Account> getAccounts() {
		return Collections.unmodifiableList(accounts);
	}

}
