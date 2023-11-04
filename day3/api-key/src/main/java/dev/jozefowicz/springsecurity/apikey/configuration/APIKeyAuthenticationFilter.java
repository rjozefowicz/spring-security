package dev.jozefowicz.springsecurity.apikey.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class APIKeyAuthenticationFilter extends GenericFilterBean {

    private final APIKeyRepository apiKeyRepository;

    public APIKeyAuthenticationFilter(APIKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var request = (HttpServletRequest)servletRequest;
        var apiKey = request.getHeader("Authorization");

        if (apiKey != null) {
            Optional<APIKeyRepository.APIKey> key = apiKeyRepository.findByKey(apiKey);
            if (key.isPresent()) {
                SecurityContextHolder.getContext().setAuthentication(
                        UsernamePasswordAuthenticationToken.authenticated(key.get().getUsername(), null,
                                List.of(new CustomGrantedAuthority(key.get().getRole())))
                );
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
