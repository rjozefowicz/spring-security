package dev.jozefowicz.jwt.jwtexample.config;

import dev.jozefowicz.jwt.jwtexample.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * TODO
 * 1. wykorzystujac JwtService zweryfikuj token
 * 2. token odczytaj z headera z requestu HTTP
 * 3. jezeli token jest poprawny ustaw  w SecurityContextHolderze zuwierzytelniona instancje Authentication
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }
}
