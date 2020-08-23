package br.com.infoway.cashmachine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.cashmachine.models.Account;
import br.com.infoway.cashmachine.models.Movement;
import br.com.infoway.cashmachine.repositories.AccountRepository;
import br.com.infoway.cashmachine.repositories.MovementRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private MovementRepository movementRepository;

	public List<Account> getAccounts() {
		return this.accountRepository.findAll();
	}

	public Account save(Account account) {
		return this.accountRepository.save(account);
	}

	public Account getAccountById(Long idAccount) {
		return this.accountRepository.findById(idAccount).get();
	}

	public List<Movement> getMovements(Long idAccount) {

		List<Movement> movements = movementRepository.findByAccountId(idAccount);
		return movements;

	}

}
