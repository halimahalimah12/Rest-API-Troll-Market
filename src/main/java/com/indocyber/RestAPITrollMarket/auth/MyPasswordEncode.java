package com.indocyber.RestAPITrollMarket.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncode extends BCryptPasswordEncoder {
}
