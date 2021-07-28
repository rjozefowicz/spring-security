package dev.jozefowicz.springsecurity.apikey.configuration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

import static java.util.Objects.nonNull;

public class APIKeyAuthenticationFilter implements Filter {

    private final APIKeyRepository apiKeyRepository;

    public APIKeyAuthenticationFilter(APIKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            final String apiKey = httpServletRequest.getHeader("Authorization");
            if (nonNull(apiKey) && !apiKey.isEmpty()) {
                apiKeyRepository.findByKey(apiKey).ifPresent(persistedApiKey -> {
                    final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(persistedApiKey.getUsername(), null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
