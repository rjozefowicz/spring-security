package dev.jozefowicz.springsecurity.sessionmanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("user1")
                .authorities(Collections.emptyList())
                .and()
                .withUser("user2")
                .password("user2")
                .authorities(Collections.emptyList())
                .and()
                .withUser("user3")
                .password("user3")
                .authorities(Collections.emptyList());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement(sessionManagement -> {
                    sessionManagement
                            .invalidSessionUrl("/session/invalid")
                            .sessionFixation(sessionFixation -> {
                                sessionFixation.newSession();
                            })
                            .maximumSessions(2)
                            .sessionRegistry(sessionRegistry())
                            .expiredUrl("/session/expired");
                })
                .csrf(csrf -> {
                    csrf.disable();
                })
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .antMatchers("/login", "/session/expired", "/session/invalid").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
