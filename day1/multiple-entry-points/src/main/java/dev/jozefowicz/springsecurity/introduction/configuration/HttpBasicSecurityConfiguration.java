package dev.jozefowicz.springsecurity.introduction.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@EnableWebSecurity
@Order(1)
public class HttpBasicSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(username -> {
                    if ("admin-jan".equals(username)) {
                        return new PersistedUserDetails("admin-jan", "admin00");
                    }
                    throw new UsernameNotFoundException("");
                });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admin/**")
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .anyRequest()
                            .authenticated();
                })
                .httpBasic();
    }

}
