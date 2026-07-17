package com.sportflow.booking.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // 1. Scarta chi non ha il biglietto o ce l'ha nel formato sbagliato
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; // Interrompiamo qui, ci penserà Spring Security a bloccarlo poi
        }

        // 2. Estrai la vera e propria stringa del token (saltando i primi 7 caratteri "Bearer ")
        final String jwt = authHeader.substring(7);

        // 3. Spacchetta il biglietto! (Se è falso o scaduto, esplode da solo con un'eccezione qui)
        final String username = jwtService.extractUsername(jwt);
        final String role = jwtService.extractRole(jwt);

        // 4. Se tutto è andato bene, ufficializziamo l'ingresso su Spring Security
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Trasformiamo la stringa "ROLE_USER" nell'oggetto ufficiale di Spring
            var authorities = List.of(new SimpleGrantedAuthority(role));

            // Creiamo il Pass universale
            var authToken = new UsernamePasswordAuthenticationToken(
                    username, null, authorities
            );

            // Diciamo al SecurityContext (la guardia centrale) che questo utente può entrare
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        // 5. Passa la palla al prossimo filtro o al Controller finale
        filterChain.doFilter(request, response);
    }
}
