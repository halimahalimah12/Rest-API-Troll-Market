package com.indocyber.RestAPITrollMarket.auth;

public interface AuthService {
    AuthTokenResponseDto creatToken(AuthTokenRequestDto dto);
}
