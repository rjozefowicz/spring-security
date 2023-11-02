package dev.jozefowicz.jwt.jwtexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static dev.jozefowicz.jwt.jwtexample.model.Users.users;

// TODO - klasa z konfiguracja security
public class SecurityConfiguration {

    /**
     * TODO
     * 1. /api/token POST publicznie dostepne
     * 2. reszta endpointow zabezpieczona
     * 3. brak sesji i csrf
     * 4. do uwierzytelniania wykorzystaj filtr JwtAuthenticationFilter zdefiniowany przed UsernamePasswordAuthenticationFilter
     * @param http
     * @return
     * @throws Exception
     */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(users);
    }

    // TODO zdefiniuj beana z AuthenticationManagerem

}
