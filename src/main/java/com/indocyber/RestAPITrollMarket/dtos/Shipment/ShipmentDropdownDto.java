package com.indocyber.RestAPITrollMarket.dtos.Shipment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipmentDropdownDto {
    private final Integer id;
    private final String name;
}
