package br.com.infoway.cashmachine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.cashmachine.models.Bank;
import br.com.infoway.cashmachine.repositories.BankRepository;

@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;

	public List<Bank> getBanks() {
		return bankRepository.findAll();
	}

	public Bank save(Bank bank) {
		return this.bankRepository.save(bank);
	}

}
