package dev.jozefowicz.springsecurity.jwtadvanced.controller;

import dev.jozefowicz.springsecurity.jwtadvanced.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static dev.jozefowicz.springsecurity.jwtadvanced.configuration.SecurityConfiguration.AUTHORIZATION_HEADER;

@RestController
@RequestMapping("/")
public class AuthenticationController {

    public static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private TokenService tokenService;

    @PostMapping("/auth/login")
    public ResponseEntity<Void> login(@RequestBody @Validated LoginCredentialsDTO loginCredentials, HttpServletResponse response) {

        // TODO wywołaj authenticate z AuthenticationManagera. Jeżeli uwierzytelnianie się uda to nie poleci wyjątek i można procesować dalej

        // pobierz z UserDetailsService użytkownika i wygeneruj token, zwróć go w headerze HTTP jako Authorization header

        return null;
    }

    @PostMapping("/auth/logout")
    public void logout() {
        // TODO pobierz token z headera i wywołaj tokenService.revoke(...)
    }

    @PostMapping("/auth/token")
    public ResponseEntity<Void> refresh(@RequestHeader(AUTHORIZATION_HEADER) String token, HttpServletResponse response) {
        // TODO pobierz token z headera, wywołaj tokenService.revoke(...)
        // pobierz użytkownika z SecurityContextu i wygeneruj nowy token, zwróć go jako Authroization header
        return null;
    }

    private void generateTokenAndAddToHeader(HttpServletResponse response, UserDetails userDetails) {
        String token = tokenService.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
        response.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
    }

}
