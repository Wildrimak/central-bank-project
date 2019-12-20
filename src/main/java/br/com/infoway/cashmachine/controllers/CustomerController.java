package br.com.infoway.cashmachine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.cashmachine.dto.CustomerDto;
import br.com.infoway.cashmachine.models.Account;
import br.com.infoway.cashmachine.models.Customer;
import br.com.infoway.cashmachine.services.AccountService;
import br.com.infoway.cashmachine.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AccountService accountService;

	@GetMapping
	public List<Customer> getCustomers() {

		List<Customer> customers = customerService.getCustomers();
		return customers;

	}

	@PostMapping("/accounts")
	public Customer postCustomer(@RequestBody CustomerDto customerDto) {

		Customer customer = customerDto.getCustomer();

		return customerService.save(customer);
	}

	// * GET /customer/{id}/accounts/{id}

	@GetMapping("/accounts/{idAccount}")
	public Account getAnyAccount(@PathVariable Long idAccount) {
		System.out.println("ENTREI AQUI");
		Account account = accountService.getAccountById(idAccount);
		return account;
	}

}
