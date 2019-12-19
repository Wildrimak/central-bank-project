package br.com.infoway.cashmachine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.cashmachine.models.Agency;
import br.com.infoway.cashmachine.repositories.AgencyRepository;

@Service
public class AgencyService {

	@Autowired
	private AgencyRepository agencyRepository;
	
	public List<Agency> getAgencies(){
		return agencyRepository.findAll();
	}

	public Agency save(Agency agency) {
		return agencyRepository.save(agency);
	}
	
}
