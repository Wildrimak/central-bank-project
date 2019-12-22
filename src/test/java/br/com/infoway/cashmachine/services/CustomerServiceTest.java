package br.com.infoway.cashmachine.services;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.infoway.cashmachine.exceptions.EmailCannotBeTheSameException;
import br.com.infoway.cashmachine.models.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	private Customer customer;

	@Before
	public void setUp() {
		customer = new Customer("Customer's Bank", "customer@gmail.com", "P4SSw0rd", "543.321.770-10", null);
	}

	@Test(expected = EmailCannotBeTheSameException.class)
	public void emailCannotBeTheSameAsAnEmailAlreadyRegistered() {

		Customer customerFirst = customerService.save(this.customer);
		Customer customerSecond = customerService.save(this.customer);

		assertNotEquals(customerFirst.getEmail(), customerSecond.getEmail());

	}

}
