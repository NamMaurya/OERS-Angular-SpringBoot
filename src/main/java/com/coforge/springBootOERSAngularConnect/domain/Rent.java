package com.coforge.springBootOERSAngularConnect.domain;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Rent_Details")
public class Rent {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long rent_id;
	
	private int no_of_days;
	private int product_quantity;
	@OneToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="customer_id")
	private Customer c;
	@OneToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="Product_id")
	private Product p;
	
	public Rent() {
		// TODO Auto-generated constructor stub
	}
	
	public Rent(int no_of_days, int product_quantity, Customer c, Product p) {
		super();
		this.no_of_days = no_of_days;
		this.product_quantity = product_quantity;
		this.c = c;
		this.p = p;
	}

	public long getRent_id() {
		return rent_id;
	}

	public void setRent_id(long rent_id) {
		this.rent_id = rent_id;
	}

	public int getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(int no_of_days) {
		this.no_of_days = no_of_days;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
	}
	
	
	
	
}
