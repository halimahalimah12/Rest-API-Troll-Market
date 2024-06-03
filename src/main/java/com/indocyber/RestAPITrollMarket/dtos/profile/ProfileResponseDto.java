package com.indocyber.RestAPITrollMarket.dtos.profile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponseDto {
    private final Double balance;
}
