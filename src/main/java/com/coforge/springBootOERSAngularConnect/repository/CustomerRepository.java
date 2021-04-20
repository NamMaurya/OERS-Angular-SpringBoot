package com.coforge.springBootOERSAngularConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coforge.springBootOERSAngularConnect.domain.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
