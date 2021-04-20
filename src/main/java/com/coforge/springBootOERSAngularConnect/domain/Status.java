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
@Table(name = "Status_Details")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long status_Id;

	public long getStatus_Id() {
		return status_Id;
	}

	public void setStatus_Id(long status_Id) {
		this.status_Id = status_Id;
	}

	private String returned_or_not;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "rent_id")
	private Rent r;

	public Rent getR() {
		return r;
	}

	public void setR(Rent r) {
		this.r = r;
	}

	public Status(String returned_or_not, Rent r) {
		super();
		this.returned_or_not = returned_or_not;
		this.r = r;
	}

	public String getReturned_or_not() {
		return returned_or_not;
	}

	public void setReturned_or_not(String returned_or_not) {
		this.returned_or_not = returned_or_not;
	}

	public Status() {
		// TODO Auto-generated constructor stub
	}
}
