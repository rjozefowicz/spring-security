package dev.jozefowicz.springsecurity.jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JwtAuthorizationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Authentication authentication = getAuthentication(request);
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // TODO # ustaw w kontekscie security użytkownika
        filterChain.doFilter(request, response);
    }

    /**
     *
     * @param request
     * @return TODO zwróć null jak niepoprawny token (brak tokena, nie zaczyna się od Bearer)
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        // TODO #1 pobierz token z headera HTTP (nazwa headera SecurityConstants.TOKEN_HEADER)
        String token = "FIXME";

        // TODO #2 zwróć null jak niepoprawny token (brak tokena, nie zaczyna się od Bearer)

            try {
                byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

                Jws<Claims> parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace("Bearer ", ""));

                // TODO #3 pobierz z sparsowanego tokena użytkownika (subject)
                String username = "FIXME";

                // TODO #4 pobierz role użytkownika z sparsowanego tokena (atrybut rol) i zwróć listę GrantedAuthority. Pamiętaj, że to interesują nas role a nie Authority
                List<GrantedAuthority> authorities = Collections.emptyList();

                if (username != null && !username.isEmpty()) {
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                }
            } catch (Exception exception) {
                System.out.println(exception.getLocalizedMessage());
            }

        return null;
    }
}
