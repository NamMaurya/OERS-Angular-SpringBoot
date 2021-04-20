package com.coforge.springBootOERSAngularConnect.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product_Details")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long product_id;

	private String product_name;

	public Product() {
		// TODO Auto-generated constructor stub
	}
	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Product(String product_name) {
		super();
		this.product_name = product_name;
	}

}