package com.indocyber.RestAPITrollMarket.auth;

import com.indocyber.RestAPITrollMarket.auth.jwt.JwtService;
import com.indocyber.RestAPITrollMarket.dtos.excaption.EntityNotFoundExcaption;
import com.indocyber.RestAPITrollMarket.models.Account;
import com.indocyber.RestAPITrollMarket.repositories.AccountRepository;
import com.indocyber.RestAPITrollMarket.services.AccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements AuthService , UserDetailsService {

    private  final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final AccountService accountService;

    private  final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AccountRepository accountRepository, JwtService jwtService, AccountService accountService, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.jwtService = jwtService;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    private Account findByAccount(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundExcaption("Account tidak terdaftar"));

    }

    @Override
    public AuthTokenResponseDto creatToken(AuthTokenRequestDto dto) {
        var accounts = accountRepository.findById(dto.getUsername());

        if (accounts.isEmpty()){
            return AuthTokenResponseDto.builder()
                    .token(null)
                    .message("Username Belum Terdaftar")
                    .build();
        }

        Account account = findByAccount(dto.getUsername());
        if (!passwordEncoder.matches(dto.getPassword(), account.getPassword())) {
            return AuthTokenResponseDto.builder()
                    .token(null)
                    .message("Username or passoword is incorrect")
                    .build();
        }


        return AuthTokenResponseDto.builder()
                .token(jwtService.generateToken(account))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var account = accountRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return MyAccountDetail.builder()
                .account(account)
                .accountService(accountService)
                .build();

    }
}
