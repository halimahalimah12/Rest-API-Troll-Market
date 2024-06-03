package com.indocyber.RestAPITrollMarket.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<AuthTokenResponseDto> createToken(@RequestBody AuthTokenRequestDto dto) {
        if (dto.getUsername().isBlank() || dto.getPassword().isBlank()){
            return ResponseEntity.badRequest().body(AuthTokenResponseDto.builder()
                            .token(null)
                            .message("Username atau password kosong!")
                    .build());
        }
        return ResponseEntity.ok(service.creatToken(dto));
    }

}
