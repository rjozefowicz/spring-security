package dev.jozefowicz.springsecurity.jwt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        // TODO #1 ustaw URL /login (SecurityConstants.AUTH_LOGIN_URL) jako wymagający uwierzytelnienia
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        final Optional<CredentialsDTO> credentials = parseCredentials(request);

        // TODO #2 zbuduj UsernamePasswordAuthenticationToken na podstawie credentials
        // TODO #3 wykorzystaj AuthenticationManagera do autentykacji i zwróć wynik
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) {

        // TODO #4 pobierz użytkownika
        User user = null;

        // TODO #5 pobierz role
        List<String> roles = Collections.emptyList();

        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 minut
                .claim("rol", roles)
                .compact();

        // TODO #6 dodaj do response HTTP token. Wykorzystaj SecurityConstants.TOKEN_HEADER oraz SecurityConstants.TOKEN_PREFIX
    }

    /**
     * Pomocnicza metoda do parsowania body HTTP requesta
     * @param request
     * @return
     */
    private Optional<CredentialsDTO> parseCredentials(HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                final String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                return Optional.of(objectMapper.readValue(requestBody, CredentialsDTO.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

}
