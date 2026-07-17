package com.sportflow.booking.config;

import com.sportflow.booking.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Magia pura: abilita il controllo degli accessi (@PreAuthorize) direttamente sui Controller!
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                // Regola d'oro dei Microservizi: NIENTE SESSIONI in RAM, siamo STATELESS!
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Diciamo a Spring di far passare in teoria tutti...
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())

                // ... MA mettiamo il nostro Filtro all'inizio della fila!
                // Lui leggerà l'header, spacchetterà il JWT e appenderà il "Ruolo" al petto dell'utente.
                // Saranno poi i Controller (tramite @PreAuthorize) a decidere se far passare o meno l'utente!
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
