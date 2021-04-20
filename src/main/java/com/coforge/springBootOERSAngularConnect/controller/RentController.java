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
import com.coforge.springBootOERSAngularConnect.domain.Product;
import com.coforge.springBootOERSAngularConnect.domain.Rent;
import com.coforge.springBootOERSAngularConnect.exception.ResourceNotFoundException;
import com.coforge.springBootOERSAngularConnect.repository.CustomerRepository;
import com.coforge.springBootOERSAngularConnect.repository.ProductRepository;
import com.coforge.springBootOERSAngularConnect.repository.RentRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/oers")
public class RentController {
	@Autowired
	private RentRepository rentRepository;
	@Autowired
	private ProductRepository pr;
	@Autowired
	private CustomerRepository cr;
	
	@GetMapping("/rents")
	public List<Rent> getAllRent(){
		return rentRepository.findAll();
	}
	@GetMapping("/rents/{id}")
	public ResponseEntity<Rent> getRentById(@PathVariable(value="id") Long rentId) throws ResourceNotFoundException{
		Rent rent=rentRepository.findById(rentId).orElseThrow(()-> new ResourceNotFoundException("rent not found for this id:: "+rentId));
		return ResponseEntity.ok().body(rent);
	}
	
	@PostMapping("/rents")
	public Rent createRent(@Validated @RequestBody Rent rent)
	{
		Product p = pr.findById(rent.getP().getProduct_id()).orElseThrow(null);
		Customer c = cr.findById(rent.getC().getCustomer_id()).orElseThrow(null);
		rent.setC(c);
		rent.setP(p);
		return rentRepository.save(rent);
	}
	@PutMapping("rents/{id}")
	public ResponseEntity<Rent> updateRent(@PathVariable(value="id")Long rentId,@Validated @RequestBody Rent rentDetails)
			throws ResourceNotFoundException{
		Rent rent=rentRepository.findById(rentId)
				.orElseThrow(()-> new ResourceNotFoundException("rent not found for this id:: "+rentId));
	rent.setProduct_quantity(rentDetails.getProduct_quantity());	
	rent.setNo_of_days(rentDetails.getNo_of_days());
	rent.setP(rentDetails.getP());
	rent.setC(rentDetails.getC());

	
	final Rent updatedRent=rentRepository.save(rent);
	return ResponseEntity.ok(updatedRent);
	}
	
	@DeleteMapping("/rents/{id}")
	public Map<String, Boolean> deleteRent(@PathVariable(value="id") Long rentId) throws ResourceNotFoundException{
		Rent rent=rentRepository.findById(rentId)
				.orElseThrow(()-> new ResourceNotFoundException("rent not found for this id:: "+rentId));
		rentRepository.delete(rent);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	return response;
		
	}
}

