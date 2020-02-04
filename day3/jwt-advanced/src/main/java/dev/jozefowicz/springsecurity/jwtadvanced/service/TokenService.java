package dev.jozefowicz.springsecurity.jwtadvanced.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TokenService {

    private String tokenKey ="asdf7a9s8d7f89as7df897sd98f7a98sd7f9asd7f987asd98f7asdfksahdfkhajskdhfkasd9f7a9sa8sd7f";

    private int tokenDuration = 10 * 60 * 1000;

    @Autowired
    private RevokedTokenRepository revokedTokenRepository;

    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", authorities.stream().map(authority -> authority.getAuthority()).collect(Collectors.toList()));
        return Jwts.builder()
            .setSubject(email)
            .setExpiration(new Date((new Date()).getTime() + tokenDuration))
            .addClaims(claims)
            .signWith(SignatureAlgorithm.HS512, tokenKey.getBytes())
            .compact();
    }

    public UserDetails verifyTokenAndGetUserDetails(String token) {
        if (isNull(token)) {
            throw new InvalidTokenException("Token must be present");
        }
        if (revokedTokenRepository.isTokenRevoked(token)) {
            throw new InvalidTokenException("Token revoked");
        }
        try {
            final Jws<Claims> claims = Jwts.parser().setSigningKey(tokenKey.getBytes()).parseClaimsJws(token);
            List<String> roles = claims.getBody().get("rol", List.class);
            return new User(claims.getBody().getSubject(), "", roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
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
