package dev.jozefowicz.springsecurity.formlogin2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                    .withUser("jan")
                    .password("kowalski")
                    .authorities(Collections.emptyList())
                .and()
                    .withUser("stefan")
                    .password("kisielewski")
                    .authorities(Collections.emptyList());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests(authorizeRequests -> {
           authorizeRequests
                   .antMatchers("/login")
                   .permitAll()
                   .anyRequest()
                   .authenticated();
       }).formLogin(formLogin -> {
            formLogin
                    .loginPage("/login")
                    .passwordParameter("pass")
                    .usernameParameter("user")
                    .loginProcessingUrl("/doLogin");
       }).logout();
    }
}
