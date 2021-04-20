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


import com.coforge.springBootOERSAngularConnect.domain.Customer;
import com.coforge.springBootOERSAngularConnect.exception.ResourceNotFoundException;
import com.coforge.springBootOERSAngularConnect.repository.CustomerRepository;



@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/oers")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value="id") Long customerId) throws ResourceNotFoundException{
		Customer customer=customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("customer not found for this id:: "+customerId));
		return ResponseEntity.ok().body(customer);
	}
	
	@PostMapping("/customers")
	public Customer createCustomer(@Validated @RequestBody Customer customer)
	{
		return customerRepository.save(customer);
	}
	@PutMapping("customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value="id")Long customerId, @Validated @RequestBody Customer customerDetails)
			throws ResourceNotFoundException{
		Customer customer=customerRepository.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException("customer not found for this id:: "+customerId));
	customer.setCustomer_name(customerDetails.getCustomer_name());	
	customer.setCustomer_mobile(customerDetails.getCustomer_mobile());
	customer.setCustomer_gov_id_type(customerDetails.getCustomer_gov_id_type());
	customer.setCustomer_gov_id_number(customerDetails.getCustomer_gov_id_number());
	customer.setCustomer_occupation(customerDetails.getCustomer_occupation());
	customer.setCustomer_occupation_details(customerDetails.getCustomer_occupation_details());
	
	final Customer updatedCustomer=customerRepository.save(customer);
	return ResponseEntity.ok(updatedCustomer);
	}
	
	@DeleteMapping("/customers/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value="id") Long customerId) throws ResourceNotFoundException{
		Customer customer=customerRepository.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException("customer not found for this id:: "+customerId));
		customerRepository.delete(customer);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	return response;
		
	}
}
