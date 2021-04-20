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

import com.coforge.springBootOERSAngularConnect.domain.Bill;
import com.coforge.springBootOERSAngularConnect.domain.Rent;
import com.coforge.springBootOERSAngularConnect.exception.ResourceNotFoundException;
import com.coforge.springBootOERSAngularConnect.repository.BillRepository;
import com.coforge.springBootOERSAngularConnect.repository.RentRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/oers")
public class BillController {
	@Autowired
	private BillRepository billRepository;
	@Autowired
	private RentRepository rr;
	
	@GetMapping("/bills")
	public List<Bill> getAllBill(){
		return billRepository.findAll();
	}
	@GetMapping("/bills/{id}")
	public ResponseEntity<Bill> getBillById(@PathVariable(value="id") Long billId) throws ResourceNotFoundException{
		Bill bill=billRepository.findById(billId).orElseThrow(()-> new ResourceNotFoundException("bill not found for this id:: "+billId));
		return ResponseEntity.ok().body(bill);
	}
	
	@PostMapping("/bills")
	public Bill createBill(@Validated @RequestBody Bill bill)
	{
		Rent r = rr.findById(bill.getR().getRent_id()).orElseThrow(null);
		bill.setR(r);
		return billRepository.save(bill);
	}
	@PutMapping("bills/{id}")
	public ResponseEntity<Bill> updateBill(@PathVariable(value="id")Long billId,@Validated @RequestBody Bill billDetails)
			throws ResourceNotFoundException{
		Bill bill=billRepository.findById(billId)
				.orElseThrow(()-> new ResourceNotFoundException("bill not found for this id:: "+billId));
	bill.setAmount_paid(billDetails.getAmount_paid());	
	bill.setTotal_amount(billDetails.getTotal_amount());
	bill.setR(billDetails.getR());
	
	
	final Bill updatedBill=billRepository.save(bill);
	return ResponseEntity.ok(updatedBill);
	}
	
	@DeleteMapping("/bills/{id}")
	public Map<String, Boolean> deleteBill(@PathVariable(value="id") Long billId) throws ResourceNotFoundException{
		Bill bill=billRepository.findById(billId)
				.orElseThrow(()-> new ResourceNotFoundException("bill not found for this id:: "+billId));
		billRepository.delete(bill);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	return response;
		
	}
}

