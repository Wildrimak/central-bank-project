package br.com.infoway.cashmachine.services;

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

		List<Customer> customers = customerRepository.findAll();
		return customers;

	}

	public Customer save(Customer customer) {

		return customerRepository.save(customer);
	}

}
