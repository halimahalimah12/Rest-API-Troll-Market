package com.indocyber.RestAPITrollMarket.dtos.cart;

import com.indocyber.RestAPITrollMarket.models.CartId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartRowResponseDto {
    private final CartId cartId;
    private final String nameProduct;
    private final Integer quantity;
    private final String shipment;
    private final  String seller;
    private final Double totalPrice;
}
