package br.com.infoway.cashmachine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.cashmachine.dto.CustomerDto;
import br.com.infoway.cashmachine.models.Customer;
import br.com.infoway.cashmachine.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public List<Customer> getCustomers() {

		List<Customer> customers = customerService.getCustomers();
		return customers;

	}

	@PostMapping("/accounts")
	public Customer postCustomer(@RequestBody CustomerDto customerDto) {
		
		Customer customer = customerDto.getCustomer();

		System.out.println(customerDto.getCpf());
		System.out.println(customerDto.getEmail());
		System.out.println(customerDto.getFullName());
		System.out.println(customerDto.getPassword());
		System.out.println(customerDto.getBirthDate());
		
		return customerService.save(customer);
	}

}
