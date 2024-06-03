package com.indocyber.RestAPITrollMarket.auth.jwt;

import com.indocyber.RestAPITrollMarket.models.Account;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(Account account);
    Boolean isValid(String token, UserDetails userDetails);
    Claims getClaims(String token);
}
