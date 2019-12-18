package br.com.infoway.cashmachine.models;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Bank {

	private Long id;
	private String name;
	private Date creationDate;

	private List<Agency> agencies;
	private List<AccountOpeningProcess> accountOpeningProcesses;

	public Bank() {
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

	public List<AccountOpeningProcess> getAccountOpeningProcesses() {
		return Collections.unmodifiableList(accountOpeningProcesses);
	}

}
