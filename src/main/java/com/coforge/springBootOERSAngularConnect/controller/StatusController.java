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
import com.coforge.springBootOERSAngularConnect.domain.Status;
import com.coforge.springBootOERSAngularConnect.exception.ResourceNotFoundException;
import com.coforge.springBootOERSAngularConnect.repository.RentRepository;
import com.coforge.springBootOERSAngularConnect.repository.StatusRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/oers")
public class StatusController {
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private RentRepository rr;
	
	@GetMapping("/statuss")
	public List<Status> getAllStatus(){
		return statusRepository.findAll();
	}
	@GetMapping("/statuss/{id}")
	public ResponseEntity<Status> getStatusById(@PathVariable(value="id") Long statusId) throws ResourceNotFoundException{
		Status status=statusRepository.findById(statusId).orElseThrow(()-> new ResourceNotFoundException("status not found for this id:: "+statusId));
		return ResponseEntity.ok().body(status);
	}
	
	@PostMapping("/statuss")
	public Status createStatus(@Validated @RequestBody Status status)
	{
		Rent r = rr.findById(status.getR().getRent_id()).orElseThrow(null);
		status.setR(r);
		return statusRepository.save(status);
	}
	@PutMapping("statuss/{id}")
	public ResponseEntity<Status> updateStatus(@PathVariable(value="id")Long statusId,@Validated @RequestBody Status statusDetails)
			throws ResourceNotFoundException{
		Status status=statusRepository.findById(statusId)
				.orElseThrow(()-> new ResourceNotFoundException("status not found for this id:: "+statusId));
	status.setReturned_or_not(statusDetails.getReturned_or_not());	
	status.setR(statusDetails.getR());
	

	
	final Status updatedStatus=statusRepository.save(status);
	return ResponseEntity.ok(updatedStatus);
	}
	
	@DeleteMapping("/statuss/{id}")
	public Map<String, Boolean> deleteStatus(@PathVariable(value="id") Long statusId) throws ResourceNotFoundException{
		Status status=statusRepository.findById(statusId)
				.orElseThrow(()-> new ResourceNotFoundException("status not found for this id:: "+statusId));
		statusRepository.delete(status);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	return response;
		
	}
}

