package com.coforge.springBootOERSAngularConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coforge.springBootOERSAngularConnect.domain.TypeOfProduct;



@Repository
public interface TypeOfProductRepository extends JpaRepository<TypeOfProduct,Long> {

}
