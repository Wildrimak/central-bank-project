package br.com.infoway.cashmachine.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.cashmachine.models.Agency;
import br.com.infoway.cashmachine.models.Bank;
import br.com.infoway.cashmachine.repositories.AgencyRepository;
import br.com.infoway.cashmachine.repositories.BankRepository;

@Service
public class AgencyService {

	@Autowired
	private AgencyRepository agencyRepository;

	@Autowired
	private BankRepository bankRepository;

	public List<Agency> getAgencies() {
		return agencyRepository.findAll();
	}

	public Agency save(Agency agency) {
		validate(agency);
		return agencyRepository.save(agency);
	}

	private void validate(Agency agency) {
		bankIdMustExist(agency);
	}

	private void bankIdMustExist(Agency agency) {

		Optional<Bank> optional = bankRepository.findById(agency.getBank().getId());

		if (optional == null) {
			throw new IllegalArgumentException("The bankId doesn't exist");
		}

	}

}
