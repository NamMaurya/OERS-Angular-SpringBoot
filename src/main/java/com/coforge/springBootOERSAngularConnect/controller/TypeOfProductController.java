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
import com.coforge.springBootOERSAngularConnect.domain.TypeOfProduct;
import com.coforge.springBootOERSAngularConnect.exception.ResourceNotFoundException;
import com.coforge.springBootOERSAngularConnect.repository.CustomerRepository;
import com.coforge.springBootOERSAngularConnect.repository.ProductRepository;
import com.coforge.springBootOERSAngularConnect.repository.TypeOfProductRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/oers")
public class TypeOfProductController {
	@Autowired
	private TypeOfProductRepository typeOfProductRepository;
	@Autowired
	private ProductRepository pr;
	
	@GetMapping("/typeOfProducts")
	public List<TypeOfProduct> getAllTypeOfProduct(){
		return typeOfProductRepository.findAll();
	}
	@GetMapping("/typeOfProducts/{id}")
	public ResponseEntity<TypeOfProduct> getTypeOfProductById(@PathVariable(value="id") Long typeOfProductId) throws ResourceNotFoundException{
		TypeOfProduct typeOfProduct=typeOfProductRepository.findById(typeOfProductId).orElseThrow(()-> new ResourceNotFoundException("typeOfProduct not found for this id:: "+typeOfProductId));
		return ResponseEntity.ok().body(typeOfProduct);
	}
	
	@PostMapping("/typeOfProducts")
	public TypeOfProduct createTypeOfProduct(@Validated @RequestBody TypeOfProduct typeOfProduct)
	{
		Product p = pr.findById(typeOfProduct.getP().getProduct_id()).orElseThrow(null);
		typeOfProduct.setP(p);
		return typeOfProductRepository.save(typeOfProduct);
	}
	@PutMapping("typeOfProducts/{id}")
	public ResponseEntity<TypeOfProduct> updateTypeOfProduct(@PathVariable(value="id")Long typeOfProductId,@Validated @RequestBody TypeOfProduct typeOfProductDetails)
			throws ResourceNotFoundException{
		TypeOfProduct typeOfProduct=typeOfProductRepository.findById(typeOfProductId)
				.orElseThrow(()-> new ResourceNotFoundException("typeOfProduct not found for this id:: "+typeOfProductId));
	typeOfProduct.setProduct_type(typeOfProductDetails.getProduct_type());	
	typeOfProduct.setProduct_specification(typeOfProductDetails.getProduct_specification());
	typeOfProduct.setProduct_rentprice(typeOfProductDetails.getProduct_rentprice());
	typeOfProduct.setP(typeOfProductDetails.getP());
	
	final TypeOfProduct updatedTypeOfProduct=typeOfProductRepository.save(typeOfProduct);
	return ResponseEntity.ok(updatedTypeOfProduct);
	}
	
	@DeleteMapping("/typeOfProducts/{id}")
	public Map<String, Boolean> deleteTypeOfProduct(@PathVariable(value="id") Long typeOfProductId) throws ResourceNotFoundException{
		TypeOfProduct typeOfProduct=typeOfProductRepository.findById(typeOfProductId)
				.orElseThrow(()-> new ResourceNotFoundException("typeOfProduct not found for this id:: "+typeOfProductId));
		typeOfProductRepository.delete(typeOfProduct);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	return response;
		
	}
}

