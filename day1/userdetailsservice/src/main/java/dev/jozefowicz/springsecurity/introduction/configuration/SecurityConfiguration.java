package dev.jozefowicz.springsecurity.introduction.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @PostConstruct
    public void setup() {
        customUserDetailsManager.createUser(new PersistedUserDetails("nikola", "tesla"));
    }

    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsManager);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .antMatchers("/login", "/invalidLogin", "/h2-console/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(formLogin -> {
                    formLogin
                            .loginPage("/login")
                            .usernameParameter("user")
                            .passwordParameter("pass")
                            .loginProcessingUrl("/doLogin")
                            .failureUrl("/invalidLogin")
                            .defaultSuccessUrl("/");
                })
                .logout(logout -> {
                    logout
                            .logoutUrl("/doLogout")
                            .logoutSuccessUrl("/login?loggedOut");
                })
                .csrf(csrf -> {
                    csrf
                            .ignoringAntMatchers("/h2-console/**");
                })
                .headers(headers -> {
                    headers.frameOptions().disable();
                });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
