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

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void fullNameCannotBeNull() {

		Customer customer = new Customer();
		customer.setFullName(null);

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertFalse(violations.isEmpty());

	}

	@Test
	public void fullNameCannotBeEmpty() {

		Customer customer = new Customer();
		customer.setFullName("");

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertFalse(violations.isEmpty());

	}

	@Test
	public void emailCannotBeNull() {

	}

	@Test
	public void emailCannotBeEmpty() {

	}

	@Test
	public void EmailCannotBeTheSameAsAnEmailAlreadyRegistered() {

	}

	@Test
	public void theEmailFieldMustHaveAnEmailAsContent() {

	}

}
