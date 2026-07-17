package com.sportflow.booking.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Questo è il vero cuore del lettore:
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey()) // Verifica che il timbro a secco sia autentico
                .build()
                .parseSignedClaims(token)   // Spacchetta il JWT
                .getPayload()               // Prende il corpo JSON
                .getSubject();              // Estrae il campo "sub" (lo username)
    }

    public String extractRole(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }
}
