package com.indocyber.RestAPITrollMarket.dtos.cart;

import com.indocyber.RestAPITrollMarket.models.CartId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartRequestDeleteDto {
    private final CartId cartId;
}
