package com.indocyber.RestAPITrollMarket.dtos.cart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseToCart {
    private Boolean isSuccess;
    private String pesan;
}
