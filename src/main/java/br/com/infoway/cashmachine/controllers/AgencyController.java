package br.com.infoway.cashmachine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.cashmachine.models.Agency;
import br.com.infoway.cashmachine.services.AgencyService;

@RestController
@RequestMapping("/agencies")
public class AgencyController {


	@Autowired
	private AgencyService agencyService;
	
	@GetMapping
	public List<Agency> getAgencies() {
		return agencyService.getAgencies();
	}
	
	@PostMapping
	public Agency save(@Validated @RequestBody Agency agency) {
		return this.agencyService.save(agency);
	}
	
}
