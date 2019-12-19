package br.com.infoway.cashmachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.cashmachine.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByFullName(String fullName);

}
