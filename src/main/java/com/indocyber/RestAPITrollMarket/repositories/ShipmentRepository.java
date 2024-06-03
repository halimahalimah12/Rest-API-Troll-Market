package com.indocyber.RestAPITrollMarket.repositories;

import com.indocyber.RestAPITrollMarket.models.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {

    @Query("""
            SELECT s
            FROM Shipment s
            WHERE s.isService = true
            """)
    List<Shipment> findAllIsService();

}
