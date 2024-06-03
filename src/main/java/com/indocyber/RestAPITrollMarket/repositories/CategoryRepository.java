package com.indocyber.RestAPITrollMarket.repositories;

import com.indocyber.RestAPITrollMarket.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
