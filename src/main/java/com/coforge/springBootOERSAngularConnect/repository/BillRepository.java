package com.coforge.springBootOERSAngularConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coforge.springBootOERSAngularConnect.domain.Bill;




@Repository
public interface BillRepository extends JpaRepository<Bill,Long>{

}
