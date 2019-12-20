package br.com.infoway.cashmachine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.cashmachine.dto.BankDto;
import br.com.infoway.cashmachine.models.Bank;
import br.com.infoway.cashmachine.services.BankService;

@RestController
@RequestMapping("/banks")
public class BankController {

	@Autowired
	private BankService bankService;

	@GetMapping
	public List<Bank> getBanks() {
		return bankService.getBanks();
	}
	
	@PostMapping
	public Bank save(@Validated @RequestBody BankDto dto) {
		Bank bank = dto.getBank();
		return this.bankService.save(bank);
	}

}
