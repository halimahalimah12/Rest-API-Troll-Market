package com.indocyber.RestAPITrollMarket.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthTokenRequestDto {
    private final String username;
    public  final String password;
}
