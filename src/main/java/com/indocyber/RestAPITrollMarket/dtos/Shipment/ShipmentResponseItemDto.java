package com.indocyber.RestAPITrollMarket.dtos.Shipment;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipmentResponseItemDto {
    private final Integer id;
    private final String name;
    private final Double price;
    private final Boolean isService;
}
