package br.com.infoway.cashmachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.cashmachine.models.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
