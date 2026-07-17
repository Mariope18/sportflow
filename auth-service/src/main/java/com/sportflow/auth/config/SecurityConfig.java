package com.sportflow.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disabilitiamo il CSRF. È una protezione che serve se hai interfacce
                // HTML con sessioni, ma noi useremo le API REST e i Token, quindi non ci serve.
                .csrf(csrf -> csrf.disable())

                // 2. Apriamo il varco: tutte le richieste verso /auth sono libere (permitAll),
                // mentre TUTTE le altre rotte del progetto richiederanno l'autenticazione.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated()
                )

                // 3. Diciamo a Spring di NON creare sessioni in memoria (STATELESS).
                // Il nostro sistema si baserà solo sui Token JWT che vedremo tra poco.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
