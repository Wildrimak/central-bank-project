package br.com.infoway.cashmachine.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Full name may not be empty")
	private String fullName;
	private String cpf;

	@NotEmpty
	@Email
	private String email;

	@JsonIgnore
	private String password;

	private Date birthDate;

	@CreationTimestamp
	private Date registrationDate;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Account> accounts;

	public Customer() {
		this.registrationDate = new Date();
		this.accounts = new ArrayList<>();
	}

	public Customer(Date birthDate, String cpf, String email, String fullName, String password) {
		this.birthDate = birthDate;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public List<Account> getAccounts() {
		return Collections.unmodifiableList(accounts);
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
