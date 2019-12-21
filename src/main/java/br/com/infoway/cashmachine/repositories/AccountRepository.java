package br.com.infoway.cashmachine.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.cashmachine.models.Account;
import br.com.infoway.cashmachine.models.Movement;

public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Movement> findByMovements(Long idAccount);
}
