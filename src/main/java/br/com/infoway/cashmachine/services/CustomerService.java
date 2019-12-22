package br.com.infoway.cashmachine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.cashmachine.exceptions.CpfCannotBeTheSameException;
import br.com.infoway.cashmachine.exceptions.CustomerMustBeOver18Exception;
import br.com.infoway.cashmachine.exceptions.EmailCannotBeTheSameException;
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
		validate(customer);
		return customerRepository.save(customer);
	}

	private void validate(Customer customer) {
		emailCannotBeTheSame(customer);
		cpfCannotBeTheSame(customer);
		customerMustBeOver18(customer);
	}

	private void emailCannotBeTheSame(Customer customer) {
		customerRepository.findAll().forEach(customerSaved -> {

			if (customer.getEmail().equals(customerSaved.getEmail())) {
				throw new EmailCannotBeTheSameException();
			}

		});

	}

	private void cpfCannotBeTheSame(Customer customer) {
		customerRepository.findAll().forEach(customerSaved -> {

			if (customer.getCpf().equals(customerSaved.getCpf())) {
				throw new CpfCannotBeTheSameException();
			}

		});

	}

	private void customerMustBeOver18(Customer customer) {

		long eighteenYearsInMilliseconds = 567648000000l;
		long customerAgeMilliseconds = customer.getBirthDate().getTime();

		if (customerAgeMilliseconds < eighteenYearsInMilliseconds) {
			throw new CustomerMustBeOver18Exception();
		}
	}

}
