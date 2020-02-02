package dev.jozefowicz.springsecurity.x509.configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated();
                /*
                    TODO #2 skonfiguruj x509
                    1. regex do user principal jako 'CN=(.*?)(?:,|$)'
                    2. dodaj user details service
                    3. session creation policy jako stateless
                 */

    }

    // TODO #1 zdefiniuj beana UserDetailsService, który dla username MD zwróci użytnika z rolą USER,
    // a dla username == MD_ADMIN zwróci użytkownika z rolą ADMIN

}