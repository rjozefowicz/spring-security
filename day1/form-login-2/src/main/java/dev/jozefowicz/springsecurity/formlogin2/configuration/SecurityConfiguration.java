package dev.jozefowicz.springsecurity.formlogin2.configuration;

import dev.jozefowicz.springsecurity.formlogin2.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            final UserRepository.User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));
            return new User(user.getUsername(), user.getPassword(), user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> {
                    authz
                            .requestMatchers("/login")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(formLogin -> {
                    formLogin
                            .loginPage("/login")
                            .loginProcessingUrl("/doLogin")
                            .usernameParameter("user")
                            .passwordParameter("pass");
                })
                .logout(logout -> {
                    logout.logoutUrl("/doLogout");
                })
                .userDetailsService(userDetailsService())
                .rememberMe(rememberMe -> {
                    rememberMe
                            .key("some-key")
                            .rememberMeCookieName("rememberMe")
                            .rememberMeParameter("rememberMe");
                })
                .sessionManagement(sessionManagement -> {
                    sessionManagement.maximumSessions(1);
                });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
