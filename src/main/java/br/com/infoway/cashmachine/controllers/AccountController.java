package br.com.infoway.cashmachine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.cashmachine.models.Account;
import br.com.infoway.cashmachine.services.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public List<Account> getAccounts() {
		return this.accountService.getAccounts();
	}

	@PostMapping("{id_account}/withdrawal")
	public Account postWithdrawal(@RequestParam("value") double value, @PathVariable Long id_account) {

		System.out.println(value);
		Account account = new Account();
		return account;

	}
}
