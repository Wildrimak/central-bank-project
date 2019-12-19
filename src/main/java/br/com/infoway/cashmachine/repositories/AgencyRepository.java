package br.com.infoway.cashmachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.cashmachine.models.Agency;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

}
