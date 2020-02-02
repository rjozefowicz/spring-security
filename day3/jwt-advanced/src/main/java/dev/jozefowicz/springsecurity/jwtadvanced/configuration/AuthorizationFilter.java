package dev.jozefowicz.springsecurity.jwtadvanced.configuration;

import dev.jozefowicz.springsecurity.jwtadvanced.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationFilter extends GenericFilterBean {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationFilter.class);

    private final TokenService tokenService;

    private final UserDetailsService userDetailsService;

    public AuthorizationFilter(TokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!isErrorPage(servletRequest)) { // kiedy tokenservice rzuci invalidtokenexception spring security probuje wyswietlać /error strone błędu
            attemptAuthorization(servletRequest);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void attemptAuthorization(ServletRequest servletRequest) {
        // TODO pobierz z headera token, jak nie istnieje zrób return;
        // TODO wywołaj tokenService.verifyTokenAndGetUserEmail(token); jak metoda się powiedzie to zwróci emaila (token jest poprawny)
        // TODO pobierz z UserDetailsService użytkownika, zbuduj UsernamePasswordAuthenticationToken i ustaw w SecurityCOntextHolderze
    }

    private boolean isErrorPage(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return request.getRequestURI().equals("/error");
    }

}
