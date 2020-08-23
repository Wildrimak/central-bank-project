package br.com.infoway.cashmachine.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.cashmachine.models.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long> {

	List<Movement> findByAccountId(Long id);
	
}
