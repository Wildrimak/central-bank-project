package br.com.infoway.cashmachine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.cashmachine.models.Account;
import br.com.infoway.cashmachine.services.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public List<Account> getAccounts(){
		return this.accountService.getAccounts();
	}
	
}
