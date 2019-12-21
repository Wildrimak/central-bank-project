package br.com.infoway.cashmachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import br.com.infoway.cashmachine.models.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long> {

	Streamable<Movement> findByAccountId(Long id);
	
}
