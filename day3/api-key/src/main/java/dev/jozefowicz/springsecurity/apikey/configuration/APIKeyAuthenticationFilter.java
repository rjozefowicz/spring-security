package dev.jozefowicz.springsecurity.apikey.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

import static java.util.Objects.nonNull;

public class APIKeyAuthenticationFilter extends OncePerRequestFilter {

    private final APIKeyRepository apiKeyRepository;

    public APIKeyAuthenticationFilter(APIKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String apiKey = request.getHeader("Authorization");
        if (nonNull(apiKey) && !apiKey.isEmpty()) {
            apiKeyRepository.findByKey(apiKey).ifPresent(persistedApiKey -> {
                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(persistedApiKey.getUsername(), null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        }

        filterChain.doFilter(request, response);
    }
}
