package br.com.infoway.cashmachine.services;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoway.cashmachine.exceptions.CpfCannotBeTheSameException;
import br.com.infoway.cashmachine.exceptions.CustomerMustBeOver18Exception;
import br.com.infoway.cashmachine.exceptions.EmailCannotBeTheSameException;
import br.com.infoway.cashmachine.models.Customer;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	private String defaultFullName = "Customer's Bank";
	private String defaultEmail = "customer@gmail.com";
	private String defaultPassword = "P4SSw0rd";
	private String defaultCpf = "543.321.770-10";
	private Date defaultDate = new Date();

	private Customer customer;

	@Before
	public void setUp() {
		customer = new Customer(defaultFullName, defaultEmail, defaultPassword, defaultCpf, defaultDate);
	}

	@Test(expected = EmailCannotBeTheSameException.class)
	public void emailCannotBeTheSameAsAnEmailAlreadyRegistered() {

		customerService.save(this.customer);
		customerService.save(this.customer);

	}

	@Test(expected = CpfCannotBeTheSameException.class)
	public void cpfCannotBeTheSameAsAnCpfAlreadyRegistered() {

		customerService.save(this.customer);

		Customer customerAnother = new Customer(defaultFullName, "other@gmail.com", defaultPassword, defaultCpf,
				defaultDate);

		customerService.save(customerAnother);

	}

	@Test(expected = CustomerMustBeOver18Exception.class)
	public void customerMustBeOver18() {

		long aboveEighteenYearsInMilliseconds = 567647999999l;

		customerService.save(this.customer);

		Customer customerAnother = new Customer(defaultFullName, "other2@gmail.com", defaultPassword, "353.156.440-49",
				new Date(aboveEighteenYearsInMilliseconds));

		customerService.save(customerAnother);

	}

}
