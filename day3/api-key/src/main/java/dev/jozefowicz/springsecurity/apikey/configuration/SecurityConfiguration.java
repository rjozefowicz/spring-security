package dev.jozefowicz.springsecurity.apikey.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


/**
 * TODO
 * 1. Ustaw politykę tworzenia sesji na STATELESS
 * 2. Stwórz nowy filtr APIKeyAuthenticationFilter (wykorzystaj GenericFilterBean albo zaimplementuj czysty interfejs z servlet API
 * 3. Dodać do Filter Chain swój nowy filtr PRZED UsernamePasswordAuthenticationFilter
 * 4. Zaimplementuj APIKeyAuthenticationFilter tak aby pobierał z zapytania HTTP header Authorization i sprawdzał przesłany klucz w ApiKeyRepository
 * 5. Jeżeli klucz istnieje ustaw w filtrze instancję Authentication w SecurityContextHolderze. Nie interesuje nas hasło ani GrantedAuthorities
 * 6. Wykorzystaj gotowe testy, przetestuj w Postmanie
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private APIKeyRepository apiKeyRepository;

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> {
                    authz
                            .requestMatchers("/**")
                            .authenticated();
                });
        return http.build();
    }
}
