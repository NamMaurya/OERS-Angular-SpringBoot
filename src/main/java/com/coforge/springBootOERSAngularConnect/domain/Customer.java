package com.coforge.springBootOERSAngularConnect.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Customer_OERS_Details")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
 private long customer_id;
private String customer_name;
private int customer_mobile;
private String customer_gov_id_type;
private int customer_gov_id_number;
private String customer_occupation;
private String customer_occupation_details;



public long getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(long customer_id) {
	this.customer_id = customer_id;
}
public String getCustomer_name() {
	return customer_name;
}
public void setCustomer_name(String customer_name) {
	this.customer_name = customer_name;
}
public int getCustomer_mobile() {
	return customer_mobile;
}
public void setCustomer_mobile(int customer_mobile) {
	this.customer_mobile = customer_mobile;
}
public String getCustomer_gov_id_type() {
	return customer_gov_id_type;
}
public void setCustomer_gov_id_type(String customer_gov_id_type) {
	this.customer_gov_id_type = customer_gov_id_type;
}
public int getCustomer_gov_id_number() {
	return customer_gov_id_number;
}

public void setCustomer_gov_id_number(int customer_gov_id_number) {
	this.customer_gov_id_number = customer_gov_id_number;
}
public String getCustomer_occupation() {
	return customer_occupation;
}
public void setCustomer_occupation(String customer_occupation) {
	this.customer_occupation = customer_occupation;
}
public String getCustomer_occupation_details() {
	return customer_occupation_details;
}
public void setCustomer_occupation_details(String customer_occupation_details) {
	this.customer_occupation_details = customer_occupation_details;
}

public Customer() {
	// TODO Auto-generated constructor stub
}
public Customer(String customer_name, int customer_mobile, String customer_gov_id_type, int customer_gov_id_number,
		String customer_occupation, String customer_occupation_details) {
	super();
	this.customer_name = customer_name;
	this.customer_mobile = customer_mobile;
	this.customer_gov_id_type = customer_gov_id_type;
	this.customer_gov_id_number = customer_gov_id_number;
	this.customer_occupation = customer_occupation;
	this.customer_occupation_details = customer_occupation_details;
}

}
