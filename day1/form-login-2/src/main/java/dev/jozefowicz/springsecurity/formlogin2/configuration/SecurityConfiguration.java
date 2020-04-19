package dev.jozefowicz.springsecurity.formlogin2.configuration;

import dev.jozefowicz.springsecurity.formlogin2.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.stream.Collectors;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(username -> {
                    final UserRepository.User user =
                            userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));
                    return new User(
                            user.getUsername(),
                            user.getPassword(),
                            user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                });
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
