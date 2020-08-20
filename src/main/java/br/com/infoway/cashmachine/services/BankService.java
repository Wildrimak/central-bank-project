package br.com.infoway.cashmachine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.cashmachine.exceptions.BankNameCannotBeRepeatedException;
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

		Bank bankExists = bankRepository.findByName(bank.getName());

		if (bankExists != null && !bankExists.equals(bank)) {
			throw new BankNameCannotBeRepeatedException("Já existe um banco com esse nome");
		}

		return this.bankRepository.save(bank);
	}

	public Bank findOne(Long idBank) {
		return bankRepository.findById(idBank).get();
	}

}
