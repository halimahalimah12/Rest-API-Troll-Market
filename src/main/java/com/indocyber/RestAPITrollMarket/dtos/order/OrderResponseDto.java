package com.indocyber.RestAPITrollMarket.dtos.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderResponseDto {
    private final LocalDate date;
    private final String seller;
    private final String buyyer;
    private final String product;
    private final String quantity;
    private final String shipment;
    private final String totalPrice;

}
