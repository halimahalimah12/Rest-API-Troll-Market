package com.indocyber.RestAPITrollMarket.dtos.profile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileRequestDto {
    private final Integer id;
    private final Double balance;

}
