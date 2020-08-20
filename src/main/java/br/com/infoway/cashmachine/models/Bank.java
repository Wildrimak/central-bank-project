package br.com.infoway.cashmachine.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "the name is required!")
	private String name;

	@CreationTimestamp
	private Date creationDate;

	@OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
	private List<Agency> agencies;

	public Bank() {
	}

	public Bank(String name) {
		this.name = name;
		this.agencies = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public List<Agency> getAgencies() {
		return Collections.unmodifiableList(agencies);
	}

}
