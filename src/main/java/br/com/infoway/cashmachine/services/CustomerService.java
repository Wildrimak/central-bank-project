package br.com.infoway.cashmachine.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.cashmachine.models.Customer;
import br.com.infoway.cashmachine.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<>();
		Customer customer1 = new Customer("Wildrimak", "123.456.789-01");
		customers.add(customer1);
		Customer customer2 = new Customer("Joana Dark", "923.856.789-61");
		customers.add(customer2);
		
		customerRepository.findAll();
		return customers;
	}

}
