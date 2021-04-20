package com.coforge.springBootOERSAngularConnect.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.context.annotation.Primary;

@Entity
@Table(name="Address_Details")
public class Address {
	
	  @Id
	  
	  @GeneratedValue(strategy=GenerationType.IDENTITY) 
	  private long a_id; 
	  public long getA_id() 
	  { return a_id; }
	  public void setA_id(long a_id) 
	  { this.a_id =a_id;
	  }
	 
private int house_no;
private String street_name;
private String city;
private int pincode;
private String state;
private String country;


@OneToOne(cascade= {CascadeType.ALL})
@JoinColumn(name="customer_id")
private Customer c;


public int getHouse_no() {
	return house_no;
}
public void setHouse_no(int house_no) {
	this.house_no = house_no;
}
public String getStreet_name() {
	return street_name;
}
public void setStreet_name(String street_name) {
	this.street_name = street_name;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public int getPincode() {
	return pincode;
}
public void setPincode(int pincode) {
	this.pincode = pincode;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}

public Customer getC() {
	return c;
}
public void setC(Customer c) {
	this.c = c;
}
public Address(int house_no, String street_name, String city, int pincode, String state, String country, Customer c) {
	super();
	this.house_no = house_no;
	this.street_name = street_name;
	this.city = city;
	this.pincode = pincode;
	this.state = state;
	this.country = country;
	this.c = c;
}



public Address() {
	// TODO Auto-generated constructor stub
}
}
