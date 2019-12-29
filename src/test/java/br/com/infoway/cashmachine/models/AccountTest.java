package br.com.infoway.cashmachine.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import br.com.infoway.cashmachine.exceptions.ValueHasToBeGreaterThanZero;
import br.com.infoway.cashmachine.exceptions.WithdrawalCannotBeHigherThanYourCurrentLimitPlusYourBalanceMinusYourFee;
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

//	

	@Test(expected = ValueHasToBeGreaterThanZero.class)
	public void theValueHasToBeGreaterThanZero() {

		BigDecimal balance = new BigDecimal(1000);
		BigDecimal limit = BigDecimal.ZERO;
		BigDecimal fee = BigDecimal.ZERO;
		Integer digit = 9;

		Account account = new Account(defaultAgency, balance, limit, defaultCustomer, fee, defaultNumber, digit);

		account.withdrawal(BigDecimal.ZERO);
		account.withdrawal(new BigDecimal(-1));

	}

//	Saldo = 3000; Limite = 2.000, Limite Atual = 2000, Taxa = 50; => O valor maximo possivel para saque deve ser 4950.
	@Test(expected = WithdrawalCannotBeHigherThanYourCurrentLimitPlusYourBalanceMinusYourFee.class)
	public void shouldNotWithdrawIfTheAmountIsGreaterThanTheAmountAvailableForWithdrawal() {

		BigDecimal balance = new BigDecimal(3000);
		BigDecimal limit = new BigDecimal(2000);
		BigDecimal fee = new BigDecimal(50);
		Integer digit = 9;

		Account account = new Account(defaultAgency, balance, limit, defaultCustomer, fee, defaultNumber, digit);

		BigDecimal amountAvailableForWithdrawal = balance.add(account.getCurrentLimit()).subtract(fee);

		account.withdrawal(amountAvailableForWithdrawal.add(BigDecimal.ONE));

	}

//	Se o saque acima for realizado, o saldo deve ficar igual a 0, o limite atual igual a 0 e não sacar os 4950 e ficar 50 reais no saldo;
	@Test
	public void theBalanceAfterASpecialWithdrawalRemainsValid() {
		
		BigDecimal balance = new BigDecimal(3000);
		BigDecimal limit = new BigDecimal(2000);
		BigDecimal fee = new BigDecimal(50);
		Integer digit = 9;

		Account account = new Account(defaultAgency, balance, limit, defaultCustomer, fee, defaultNumber, digit);

		BigDecimal amountAvailableForWithdrawal = balance.add(account.getCurrentLimit()).subtract(fee);

		account.withdrawal(amountAvailableForWithdrawal);

		assertEquals(account.getBalance(), BigDecimal.ZERO);
		assertEquals(account.getCurrentLimit(), BigDecimal.ZERO);
		assertEquals(account.getMaximumLimit(), new BigDecimal(2000));
	}

}
