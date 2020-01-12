package dev.jozefowicz.springsecurity.ipwhitelisting.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SingleIpAddressSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Single IP address
        http.authorizeRequests(authorizeRequest -> {
            authorizeRequest
                    .antMatchers("/public")
                    .permitAll()
                    .antMatchers("/restricted")
                    .hasIpAddress("127.0.0.1")
                    .anyRequest()
                    .authenticated();
        });
    }
}
