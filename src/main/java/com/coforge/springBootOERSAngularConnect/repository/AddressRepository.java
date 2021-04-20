package com.coforge.springBootOERSAngularConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coforge.springBootOERSAngularConnect.domain.Address;



@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

}
