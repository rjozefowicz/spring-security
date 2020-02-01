package dev.jozefowicz.springsecurity.introduction.configuration;

import dev.jozefowicz.springsecurity.introduction.domain.User;
import dev.jozefowicz.springsecurity.introduction.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            final Optional<User> user = userRepository.findByName(username);
            if (user.isPresent() && user.get().isEnabled()) {
                return new org.springframework.security.core.userdetails.User(
                        user.get().getName(),
                        user.get().getPassword(),
                        Collections.emptyList());
            }
            throw new UsernameNotFoundException("");
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .antMatchers("/signin", "/signup", "/verify")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(formLogin -> {
                    formLogin
                            .loginPage("/signin")
                            .loginProcessingUrl("/signin");
                })
                .logout(logout -> {
                    logout.logoutUrl("/doLogout");
                });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
