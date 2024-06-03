package com.indocyber.RestAPITrollMarket.dtos.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequestDto {
    private final Integer buyerId;
}
