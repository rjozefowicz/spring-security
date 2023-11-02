package dev.jozefowicz.jwt.jwtexample.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.security.StandardSecureDigestAlgorithms;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${token.signing.key}")
    private String tokenSigningKey;

    public String generateToken(Authentication authentication) {

        var currentDate = Instant.now();

        Key signingKey = signingKey();
        return Jwts.builder().claims(Map.of("roles", authentication.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList())))
                .subject(authentication.getName())
                .issuedAt(Date.from(currentDate))
                .expiration(Date.from(currentDate.plus(30, ChronoUnit.MINUTES)))
                .signWith(signingKey, StandardSecureDigestAlgorithms.findBySigningKey(signingKey)).compact();
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(tokenSigningKey.getBytes(StandardCharsets.UTF_8));
    }

    public UserDetails validateAndDecodeToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(signingKey()).build().parseClaimsJws(token);
        var username = claimsJws.getPayload().getSubject();
        var roles = claimsJws.getPayload().get("roles", List.class);
        User user = new User(username, "", ((List<String>) roles).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        return user;
    }
}
