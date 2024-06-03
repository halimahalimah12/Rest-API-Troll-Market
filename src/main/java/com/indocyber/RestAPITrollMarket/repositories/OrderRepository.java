package com.indocyber.RestAPITrollMarket.repositories;

import com.indocyber.RestAPITrollMarket.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("""
            SELECT COUNT(*)
            FROM Order o
            WHERE o.id = :orderId
            """)
    Integer countShipment(@Param("orderId") Integer orderId);
}
