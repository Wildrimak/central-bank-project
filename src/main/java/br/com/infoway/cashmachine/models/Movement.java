package br.com.infoway.cashmachine.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Movement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Account account;

	@Enumerated(EnumType.STRING)
	private MovementAction action;
	
	@CreationTimestamp
	private Date date;

	public Movement() {
	}
	
	public Movement(MovementAction action, Account account){
		this.action = action;
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public MovementAction getAction() {
		return action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
