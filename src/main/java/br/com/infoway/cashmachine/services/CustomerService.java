package br.com.infoway.cashmachine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.cashmachine.models.Account;
import br.com.infoway.cashmachine.models.Customer;
import br.com.infoway.cashmachine.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountService accountService;

	public List<Customer> getCustomers() {

		List<Customer> customers = customerRepository.findAll();
		return customers;

	}

	public Customer save(Customer customer) {

//		List<Account> accounts = customer.getAccounts();
//		accounts.forEach(account -> accountService.save(account));

		return customerRepository.save(customer);
	}

}
