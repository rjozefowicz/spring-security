package dev.jozefowicz.jwt.jwtexample.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.security.StandardSecureDigestAlgorithms;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * TODO - zaimplementuj serwis JWT
 */
@Service
public class JwtService {

    // TODO zdefiniuj w application.properties długi string służący do podpisania tokena
    private String tokenSigningKey;

    /**
     * TODO - zaimplementuj metodę do generowania tokena JWT. Token powinien zawierac:
     * 1. email usera
     * 2. role
     * 3. powinien byc wazny 30 minut
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {
        Key signingKey = signingKey();
        return Jwts.builder()
                // TODO
                .signWith(signingKey, StandardSecureDigestAlgorithms.findBySigningKey(signingKey)).compact();
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(tokenSigningKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * TODO zweryfikuj poprawnoś tokena i zwróc implementacje UserDetails, użyj org.springframework.security.core.userdetails.User
     * @param token
     * @return
     */
    public UserDetails validateAndDecodeToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(signingKey()).build().parseClaimsJws(token);
        return null;
    }
}
