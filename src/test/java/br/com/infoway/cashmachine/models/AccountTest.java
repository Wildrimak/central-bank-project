package br.com.infoway.cashmachine.models;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.infoway.cashmachine.repositories.BankRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {

	@Autowired
	private BankRepository bankRepository;

	private Validator validator;
	private Bank defaultBank;
	private Agency defaultAgency;
	private Customer defaultCustomer;
	private String defaultNumber;

	@Before
	public void setUp() {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		defaultBank = bankRepository.save(new Bank("defaultBank"));
		defaultAgency = new Agency(defaultNumber, defaultBank);
		defaultCustomer = new Customer("default", "default@gmail.com", "def4ult", "123.456.789-01", new Date());

	}

	@Test
	public void digitMustBeOneDigit() {

		Account account = new Account(defaultAgency, BigDecimal.ZERO, BigDecimal.ZERO, defaultCustomer, BigDecimal.ZERO,
				defaultNumber, 10);
		Set<ConstraintViolation<Account>> violations = validator.validate(account);
		assertFalse(violations.isEmpty());

	}

}
