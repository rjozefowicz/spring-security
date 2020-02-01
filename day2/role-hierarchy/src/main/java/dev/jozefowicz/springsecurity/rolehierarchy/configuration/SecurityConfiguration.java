package dev.jozefowicz.springsecurity.rolehierarchy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * TODO
 * 1. Zdefiniuj nową klasę GlobalMethodSecurityConfig rozszerzającą GlobalMethodSecurityConfiguration
 * 2. Włącz Security na poziomie metod
 * 3. Stwórz beana Springowego implementującego RoleHierarchy
 * 4. Zdefiniuj hierarchię ról:
 *      - ADMIN może to co EDITOR i READER
 *      - EDITOR może to co READER
 *      - READER jest najbardziej granularną rolą
 * 5. Przeciąż createExpressionHandler() używając nowej implementacji RoleHierarchy
 * 6. Przetestuj na endpointach wystawianych przez Admin i Document Controller
 * 7. Wykorzystujemy HTTP BASIC
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("user1")
                .authorities("ROLE_ADMIN")
                .and()
                .withUser("user2")
                .password("user2")
                .authorities("ROLE_EDITOR")
                .and()
                .withUser("user3")
                .password("user3")
                .authorities("ROLE_READER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeRequests(authorizeRequests ->
                authorizeRequests.anyRequest().authenticated())
                .httpBasic();
    }
}
