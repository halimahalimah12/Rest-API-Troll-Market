package com.indocyber.RestAPITrollMarket.dtos.cart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartRequestDto {
    private final Integer buyerId;
    private final Integer productId;
    private final Integer quantity;
    private final Integer shipmentId;
}
