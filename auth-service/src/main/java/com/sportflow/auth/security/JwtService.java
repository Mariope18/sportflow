package com.sportflow.auth.security;

import com.sportflow.auth.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    // 2. Metodo di utilità interno per trasformare la stringa segreta in una vera Chiave Crittografica (SecretKey)
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // 3. L'unica cosa che ci serve per ora: CREARE il token.
    public String generateToken(User user) {
        return Jwts.builder()
                .claim("role", "ROLE_" + user.getRole().name())
                .subject(user.getUsername()) // A chi intestiamo il biglietto? Al subject!
                .issuedAt(new Date(System.currentTimeMillis())) // Data di emissione
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration)) // Scadenza
                .signWith(getSignInKey()) // Il "timbro a secco" finale
                .compact(); // Compatta tutto in una stringa "eyJh..."
    }
}
