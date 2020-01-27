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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(username -> {
                    final Optional<User> persistedUser = userRepository.findByUsername(username);
                    if (persistedUser.isPresent()) {
                        return new PersistedUserDetails(persistedUser.get().getUsername(), persistedUser.get().getPassword());
                    }
                    throw new UsernameNotFoundException("");
                });
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
