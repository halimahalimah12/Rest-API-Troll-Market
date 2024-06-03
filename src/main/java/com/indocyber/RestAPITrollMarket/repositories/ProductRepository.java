package com.indocyber.RestAPITrollMarket.repositories;

import com.indocyber.RestAPITrollMarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
