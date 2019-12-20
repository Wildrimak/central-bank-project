package br.com.infoway.cashmachine.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.infoway.cashmachine.models.Account;
import br.com.infoway.cashmachine.models.Customer;

public class CustomerDto {

	private String fullName;
	private String email;
	private String password;
	private String cpf;
	private Date birthDate;
	private List<AccountDto> accountsDto;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<AccountDto> getAccountsDto() {
		return accountsDto;
	}

	public void setAccountsDto(List<AccountDto> contasDto) {
		this.accountsDto = contasDto;
	}

	public Customer getCustomer() {

		Customer customer = new Customer(birthDate, cpf, email, fullName, password);

		List<Account> accounts = new ArrayList<>();
		for (AccountDto accountDto : accountsDto) {
			Account account = accountDto.getAccount(customer);
			accounts.add(account);
		}

		customer.setAccounts(accounts);
		return customer;
	}
}
