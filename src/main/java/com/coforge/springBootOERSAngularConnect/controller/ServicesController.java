package com.coforge.springBootOERSAngularConnect.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.springBootOERSAngularConnect.domain.Rent;
import com.coforge.springBootOERSAngularConnect.domain.Services;
import com.coforge.springBootOERSAngularConnect.exception.ResourceNotFoundException;
import com.coforge.springBootOERSAngularConnect.repository.RentRepository;
import com.coforge.springBootOERSAngularConnect.repository.ServicesRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/oers")
public class ServicesController {
	@Autowired
	private ServicesRepository servicesRepository;
	@Autowired
	private RentRepository rr;
	
	@GetMapping("/servicess")
	public List<Services> getAllServices(){
		return servicesRepository.findAll();
	}
	@GetMapping("/servicess/{id}")
	public ResponseEntity<Services> getServicesById(@PathVariable(value="id") Long servicesId) throws ResourceNotFoundException{
		Services services=servicesRepository.findById(servicesId).orElseThrow(()-> new ResourceNotFoundException("services not found for this id:: "+servicesId));
		return ResponseEntity.ok().body(services);
	}
	
	@PostMapping("/servicess")
	public Services createServices(@Validated @RequestBody Services services)
	{
		Rent r = rr.findById(services.getR().getRent_id()).orElseThrow(null);
		services.setR(r);
		return servicesRepository.save(services);
	}
	@PutMapping("servicess/{id}")
	public ResponseEntity<Services> updateServices(@PathVariable(value="id")Long servicesId,@Validated @RequestBody Services servicesDetails)
			throws ResourceNotFoundException{
		Services services=servicesRepository.findById(servicesId)
				.orElseThrow(()-> new ResourceNotFoundException("services not found for this id:: "+servicesId));
	services.setDetails(servicesDetails.getDetails());	
	services.setR(servicesDetails.getR());
	

	
	final Services updatedServices=servicesRepository.save(services);
	return ResponseEntity.ok(updatedServices);
	}
	
	@DeleteMapping("/servicess/{id}")
	public Map<String, Boolean> deleteServices(@PathVariable(value="id") Long servicesId) throws ResourceNotFoundException{
		Services services=servicesRepository.findById(servicesId)
				.orElseThrow(()-> new ResourceNotFoundException("services not found for this id:: "+servicesId));
		servicesRepository.delete(services);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	return response;
		
	}
}

