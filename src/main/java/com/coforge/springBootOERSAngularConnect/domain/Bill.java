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
@Table(name = "Bill_Details")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bill_id;
	private int total_amount;
	private int amount_paid;
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "rent_id")
	private Rent r;

	public Bill() {
		// TODO Auto-generated constructor stub
	}

	public Rent getR() {
		return r;
	}

	public void setR(Rent r) {
		this.r = r;
	}

	public long getBill_id() {
		return bill_id;
	}

	public void setBill_id(long bill_id) {
		this.bill_id = bill_id;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public int getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(int amount_paid) {
		this.amount_paid = amount_paid;
	}

	public Bill(long bill_id, int total_amount, int amount_paid, Rent r) {
		super();
		this.bill_id = bill_id;
		this.total_amount = total_amount;
		this.amount_paid = amount_paid;
		this.r = r;
	}

	
}
