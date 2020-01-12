package dev.jozefowicz.springsecurity.jwtadvanced.controller;

import dev.jozefowicz.springsecurity.jwtadvanced.service.TokenService;
import dev.jozefowicz.springsecurity.jwtadvanced.service.WebSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static dev.jozefowicz.springsecurity.jwtadvanced.configuration.SecurityConfiguration.AUTHORIZATION_HEADER;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/")
public class AuthenticationController {

    public static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private WebSecurityService webSecurityService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/auth/login")
    public ResponseEntity<Void> login(@RequestBody @Validated LoginCredentialsDTO loginCredentials, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginCredentials.getEmail(),
                loginCredentials.getPassword())
        );

        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(loginCredentials.getEmail());

        if (nonNull(authentication) && authentication.isAuthenticated()) {
            generateTokenAndAddToHeader(response, userDetails);
            return ResponseEntity.ok().build();

        }
        return null;

    }

    @PostMapping("/auth/logout")
    public void logout(@RequestHeader(AUTHORIZATION_HEADER) String token) {
        tokenService.revokeToken(token.replace(TOKEN_PREFIX, ""));
    }

    @PostMapping("/auth/token")
    public ResponseEntity<Void> refresh(@RequestHeader(AUTHORIZATION_HEADER) String token, HttpServletResponse response) {
        tokenService.revokeToken(token.replace(TOKEN_PREFIX, ""));
        User authenticatedUser = webSecurityService.getAuthenticatedUser();
        generateTokenAndAddToHeader(response, authenticatedUser); // username == email
        return ResponseEntity.ok().build();
    }

    private void generateTokenAndAddToHeader(HttpServletResponse response, UserDetails userDetails) {
        String token = tokenService.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
        response.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
    }

}
