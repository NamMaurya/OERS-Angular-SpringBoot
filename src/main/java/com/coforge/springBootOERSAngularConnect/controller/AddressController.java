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

import com.coforge.springBootOERSAngularConnect.domain.Address;
import com.coforge.springBootOERSAngularConnect.domain.Customer;
import com.coforge.springBootOERSAngularConnect.exception.ResourceNotFoundException;
import com.coforge.springBootOERSAngularConnect.repository.AddressRepository;
import com.coforge.springBootOERSAngularConnect.repository.CustomerRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/oers")
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CustomerRepository cr;
	
	@GetMapping("/addresss")
	public List<Address> getAllAddress(){
		return addressRepository.findAll();
	}
	@GetMapping("/addresss/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable(value="id") Long addressId) throws ResourceNotFoundException{
		Address address=addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("address not found for this id:: "+addressId));
		return ResponseEntity.ok().body(address);
	}
	
	@PostMapping("/addresss")
	public Address createAddress(@Validated @RequestBody Address address)
	{
		Customer c = cr.findById(address.getC().getCustomer_id()).orElse(null);
		address.setC(c);
		return addressRepository.save(address);
	}
	@PutMapping("addresss/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable(value="id")Long addressId,@Validated @RequestBody Address addressDetails)
			throws ResourceNotFoundException{
		Address address=addressRepository.findById(addressId)
				.orElseThrow(()-> new ResourceNotFoundException("address not found for this id:: "+addressId));
	address.setCity(addressDetails.getCity());	
	address.setHouse_no(addressDetails.getHouse_no());
	address.setState(addressDetails.getState());
	address.setPincode(addressDetails.getPincode());
	address.setStreet_name(addressDetails.getStreet_name());
	address.setCountry(addressDetails.getCountry());
	address.setC(addressDetails.getC());
	final Address updatedAddress=addressRepository.save(address);
	return ResponseEntity.ok(updatedAddress);
	}
	
	@DeleteMapping("/addresss/{id}")
	public Map<String, Boolean> deleteAddress(@PathVariable(value="id") Long addressId) throws ResourceNotFoundException{
		Address address=addressRepository.findById(addressId)
				.orElseThrow(()-> new ResourceNotFoundException("address not found for this id:: "+addressId));
		addressRepository.delete(address);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	return response;
		
	}
}
