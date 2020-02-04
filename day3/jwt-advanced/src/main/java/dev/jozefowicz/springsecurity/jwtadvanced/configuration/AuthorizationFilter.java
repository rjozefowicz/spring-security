package dev.jozefowicz.springsecurity.jwtadvanced.configuration;

import dev.jozefowicz.springsecurity.jwtadvanced.controller.AuthenticationController;
import dev.jozefowicz.springsecurity.jwtadvanced.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static dev.jozefowicz.springsecurity.jwtadvanced.configuration.SecurityConfiguration.AUTHORIZATION_HEADER;
import static java.util.Objects.isNull;

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
        String header = ((HttpServletRequest) servletRequest).getHeader(AUTHORIZATION_HEADER);
        if (isNull(header) || !header.startsWith(AuthenticationController.TOKEN_PREFIX)) {
            return;
        }
        String token = header.replace(AuthenticationController.TOKEN_PREFIX, "");
        UserDetails userDetails = tokenService.verifyTokenAndGetUserDetails(token);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LOG.debug("Authorization succeeded");
    }

    private boolean isErrorPage(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return request.getRequestURI().equals("/error");
    }

}
