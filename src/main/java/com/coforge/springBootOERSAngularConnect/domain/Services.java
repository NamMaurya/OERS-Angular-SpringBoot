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
@Table(name="Service_Details")
public class Services {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long service_id;
	private String details;
	@OneToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="Rent_id")
	private Rent r;
	
	public Services() {
		// TODO Auto-generated constructor stub
	}

	public Services(String details, Rent r) {
		super();
		this.details = details;
		this.r = r;
	}

	public long getService_id() {
		return service_id;
	}

	public void setService_id(long service_id) {
		this.service_id = service_id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Rent getR() {
		return r;
	}

	public void setR(Rent r) {
		this.r = r;
	}
	
	
	
}