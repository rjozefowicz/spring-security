package dev.jozefowicz.springsecurity.multiplepasswordencoders.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user1")
                        .password("{pbkdf2}" + pbkdf2PasswordEncoder().encode("password"))
                        .roles()
                        .build(),
                User.builder()
                        .username("user2")
                        .password("{sha256}" + standardPasswordEncoder().encode("password"))
                        .roles()
                        .build(),
                User.builder()
                        .username("user3")
                        .password("{bcrypt}" + bCryptPasswordEncoder().encode("password"))
                        .roles()
                        .build()
        );
    }

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        Map<String, PasswordEncoder> passwordEncoders = new HashMap<>();
        passwordEncoders.put("pbkdf2", pbkdf2PasswordEncoder());
        passwordEncoders.put("sha256", standardPasswordEncoder());
        passwordEncoders.put("bcrypt", bCryptPasswordEncoder());
        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder("bcrypt", passwordEncoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder());
        return delegatingPasswordEncoder;
    }

    public PasswordEncoder pbkdf2PasswordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    public PasswordEncoder standardPasswordEncoder() {
        return new StandardPasswordEncoder();
    }

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
