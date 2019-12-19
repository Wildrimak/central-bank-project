package br.com.infoway.cashmachine.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Account account;

	private String action;

	public Movement() {
	}

	public Long getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public String getAction() {
		return action;
	}

}
