package com.indocyber.RestAPITrollMarket.repositories;

import com.indocyber.RestAPITrollMarket.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
    @Query("""
            SELECT s
            FROM Seller s
            WHERE s.account.username LIKE %:username%
            """)
    Seller sellerFindByAccound(@Param("username") String username);


}
