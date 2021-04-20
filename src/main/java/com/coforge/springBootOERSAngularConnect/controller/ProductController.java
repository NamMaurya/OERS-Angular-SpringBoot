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
import com.coforge.springBootOERSAngularConnect.exception.ResourceNotFoundException;
import com.coforge.springBootOERSAngularConnect.repository.CustomerRepository;
import com.coforge.springBootOERSAngularConnect.repository.ProductRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/oers")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value="id") Long productId) throws ResourceNotFoundException{
		Product product=productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("product not found for this id:: "+productId));
		return ResponseEntity.ok().body(product);
	}
	
	@PostMapping("/products")
	public Product createProduct(@Validated @RequestBody Product product)
	{
		return productRepository.save(product);
	}
	@PutMapping("products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value="id")Long productId,@Validated @RequestBody Product productDetails)
			throws ResourceNotFoundException{
		Product product=productRepository.findById(productId)
				.orElseThrow(()-> new ResourceNotFoundException("product not found for this id:: "+productId));
	product.setProduct_name(productDetails.getProduct_name());	

	
	final Product updatedProduct=productRepository.save(product);
	return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("/products/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value="id") Long productId) throws ResourceNotFoundException{
		Product product=productRepository.findById(productId)
				.orElseThrow(()-> new ResourceNotFoundException("product not found for this id:: "+productId));
		productRepository.delete(product);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	return response;
		
	}
}