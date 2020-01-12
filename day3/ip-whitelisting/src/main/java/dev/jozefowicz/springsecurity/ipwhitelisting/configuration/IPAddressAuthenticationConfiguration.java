package dev.jozefowicz.springsecurity.ipwhitelisting.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Collections;

/**
 * Configuration that adds IP address checking to authentication process. Can be checked with default login form 127.0.0.1:8080/login
 */
//@Configuration
//@EnableWebSecurity
public class IPAddressAuthenticationConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private IPAddressAuthenticationProvider ipAddressAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(ipAddressAuthenticationProvider)
                .inMemoryAuthentication()
                .withUser("user")
                .password("user").authorities(Collections.emptyList());
    }

}
