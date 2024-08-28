package com.indocyber.RestAPITrollMarket.auth;

import com.indocyber.RestAPITrollMarket.auth.jwt.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    private final JwtRequestFilter jwtRequestFilter;

    public AuthConfiguration(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
                .csrf(request -> request.disable())
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/resources/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/shipment").hasAuthority("Buyer")
                                .requestMatchers(HttpMethod.GET, "/api/v1/shipment/**").hasAuthority("Admin")
                                .requestMatchers(HttpMethod.POST, "/api/v1/shipment/**").hasAuthority("Admin")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/shipment/**").hasAuthority("Admin")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/shipment/**").hasAuthority("Admin")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/profile/**").hasAuthority("Buyer")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/merchandise/**").hasAuthority("Seller")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/merchandise/**").hasAuthority("Seller")
                                .requestMatchers(HttpMethod.GET, "/api/v1/merchandise/**").hasAuthority("Seller")
                                .requestMatchers(HttpMethod.POST,"/api/v1/cart").hasAuthority("Buyer")
                                .requestMatchers(HttpMethod.GET,"/api/v1/cart").hasAuthority("Buyer")
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/cart/**").hasAuthority("Buyer")
                                .requestMatchers(HttpMethod.GET,"/api/v1/order").hasAuthority("Admin")
                                .requestMatchers(HttpMethod.GET,"/api/v1/order/**").hasAuthority("Admin")
                                .anyRequest().authenticated())
                .httpBasic(httpBasic ->httpBasic.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandler -> exceptionHandler.accessDeniedHandler(accessDeniedHandler()))
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()));
        return http.build();
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return ((request, response, authException) -> {
            response.setStatus(401);
            response.getWriter().write("unauthorized request header");
        }) ;
    }


    private AccessDeniedHandler accessDeniedHandler() {
        return ((request, response, accessDeniedException) -> {
            response.setHeader("Content-Type","application/json");
            response.setStatus(403);
            response.getWriter().write("{\"massage\":\""+accessDeniedException.getMessage()+"\"}");
        });
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8082"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
