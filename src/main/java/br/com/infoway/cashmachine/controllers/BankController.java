package br.com.infoway.cashmachine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.cashmachine.dto.AgencyDto;
import br.com.infoway.cashmachine.dto.BankDto;
import br.com.infoway.cashmachine.models.Agency;
import br.com.infoway.cashmachine.models.Bank;
import br.com.infoway.cashmachine.services.AgencyService;
import br.com.infoway.cashmachine.services.BankService;

@RestController
@RequestMapping("/banks")
public class BankController {

	@Autowired
	private BankService bankService;

	@Autowired
	private AgencyService agencyService;

	@GetMapping
	public List<Bank> getBanks() {
		return bankService.getBanks();
	}

	@PostMapping
	public ResponseEntity<Bank> postBank(@Validated @RequestBody BankDto dto) {

		Bank bank = dto.getBank();
		bank = this.bankService.save(bank);

		return ResponseEntity.status(HttpStatus.CREATED).body(bank);

	}

	@PostMapping("{idBank}/agencies")
	public ResponseEntity<Agency> postAgency(@Validated @RequestBody AgencyDto dto, @PathVariable Long idBank) {

		Bank bank = bankService.findOne(idBank);
		Agency agency = dto.getAgency(bank);
		agency = this.agencyService.save(agency);

		return ResponseEntity.status(HttpStatus.CREATED).body(agency);

	}

}
