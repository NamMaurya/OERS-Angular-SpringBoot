package com.coforge.springBootOERSAngularConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coforge.springBootOERSAngularConnect.domain.Status;






@Repository
public interface StatusRepository extends JpaRepository<Status,Long>{

}
