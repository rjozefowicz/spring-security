package dev.jozefowicz.springsecurity.authenticationmanager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * TODO #1 - zaimplementuj komponent springowy InternalSystemAuthenticationProvider implementujący AuthenticationProvider
 *              - Wykorzystaj InternalSystemUserRepository do sprawdzenia loginu i hasła użytkownika
 *              - Wykorzystaj metodę internalSystemUser() - tylko ci użytkownicy powinni być zuwierzytelnieni tym providerem
 *              - tylko ta klasa jest wspierana przez tego providera - UsernamePasswordAuthenticationToken
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO #2 - skonfiguruj AuthenticationManagera z:
        //               - in memory dwóch użytkowników
        //               - wykorzystaj nowego AuthenticationProvidera
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
