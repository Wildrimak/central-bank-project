package br.com.infoway.cashmachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.cashmachine.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
