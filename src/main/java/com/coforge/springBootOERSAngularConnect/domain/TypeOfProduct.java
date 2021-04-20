package com.coforge.springBootOERSAngularConnect.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "TypeOfProduct_Details")
public class TypeOfProduct {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long typeOFProduct_id;

	private String product_type;
	private String product_specification;
	private int product_rentprice;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "product_id")
	private Product p;
	
	public TypeOfProduct() {
		// TODO Auto-generated constructor stub
	
	}

	public TypeOfProduct(String product_type, String product_specification, int product_rentprice, Product p) {
		super();
		this.product_type = product_type;
		this.product_specification = product_specification;
		this.product_rentprice = product_rentprice;
		this.p = p;
	}

	public long getTypeOFProduct_id() {
		return typeOFProduct_id;
	}

	public void setTypeOFProduct_id(long typeOFProduct_id) {
		this.typeOFProduct_id = typeOFProduct_id;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getProduct_specification() {
		return product_specification;
	}

	public void setProduct_specification(String product_specification) {
		this.product_specification = product_specification;
	}

	public int getProduct_rentprice() {
		return product_rentprice;
	}

	public void setProduct_rentprice(int product_rentprice) {
		this.product_rentprice = product_rentprice;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
	}

	
}

	