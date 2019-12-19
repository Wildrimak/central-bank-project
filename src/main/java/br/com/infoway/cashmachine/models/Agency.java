package br.com.infoway.cashmachine.models;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Agency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer number;
	
	@CreationTimestamp
	private Date creationDate;
	
	@ManyToOne
	@JsonIgnore
	private Bank bank;
	
	@OneToMany(mappedBy = "agency")
	@JsonIgnore
	private List<Account> accounts;

	public Agency() {
	}

	public Long getId() {
		return id;
	}

	public Integer getNumber() {
		return number;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Bank getBank() {
		return bank;
	}

	public List<Account> getAccounts() {
		return Collections.unmodifiableList(accounts);
	}

}
