package dev.jozefowicz.springsecurity.jwtadvanced.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
public class TokenService {

    private String tokenKey ="asdf7a9s8d7f89as7df897sd98f7a98sd7f9asd7f987asd98f7asdfksahdfkhajskdhfkasd9f7a9sa8sd7f";

    @Autowired
    private RevokedTokenRepository revokedTokenRepository;

    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities) {

        Map<String, Object> claims = new HashMap<>();
        // TODO dodaj do claimsów atrybut rol z listą roli użytkownika
        return Jwts.builder()
                // TODO ustaw subject jako email
                // TODO expiration date na minutę
                // TODO dodaj claimsy
            .signWith(SignatureAlgorithm.HS512, tokenKey.getBytes())
            .compact();
    }

    public String verifyTokenAndGetUserEmail(String token) {
        if (isNull(token)) {
            throw new InvalidTokenException("Token must be present");
        }
        if (revokedTokenRepository.isTokenRevoked(token)) {
            throw new InvalidTokenException("Token revoked");
        }
        try {
            return Jwts.parser().setSigningKey(tokenKey.getBytes()).parseClaimsJws(token).getBody().getSubject();
        } catch (JwtException exception) {
            throw new InvalidTokenException("Invalid token", exception);
        }
    }

    public void revokeToken(String token) {
        if (isNull(token)) {
            throw new InvalidTokenException();
        }
        long expiration = Jwts.parser().setSigningKey(tokenKey.getBytes()).parseClaimsJws(token).getBody().getExpiration().getTime();
        revokedTokenRepository.add(token, expiration);
    }


}
