package br.com.infoway.cashmachine.models;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTest {

	private Validator validator;
	private Customer customer;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		customer = new Customer("Customer's Bank", "customer@gmail.com", "P4SSw0rd", "543.321.770-10", null);
	}

	@Test
	public void fullNameCannotBeNull() {

		customer.setFullName(null);

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertFalse(violations.isEmpty());

	}

	@Test
	public void fullNameCannotBeEmpty() {

		customer.setFullName("");

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertFalse(violations.isEmpty());

	}

	@Test
	public void emailCannotBeNull() {

		customer.setEmail(null);

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertFalse(violations.isEmpty());

	}

	@Test
	public void emailCannotBeEmpty() {

		customer.setEmail("");

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertFalse(violations.isEmpty());

	}

	@Test
	public void theEmailFieldMustHaveAnEmailAsContent() {

		customer.setEmail("wildrimak");

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertFalse(violations.isEmpty());

	}

	@Test
	public void passwordCannotBeEmpty() {
		customer.setPassword("");
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertFalse(violations.isEmpty());

	}

}
