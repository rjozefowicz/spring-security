
package dev.jozefowicz.springsecurity.jwt.config;


import dev.jozefowicz.springsecurity.jwt.domain.User;
import dev.jozefowicz.springsecurity.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Optional;
import java.util.stream.Collectors;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) // TODO #3 włącz wsparcie dla adnotacji @PreAuthorize
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
                // TODO #1 dodaj dwa filtry: JwtAuthenticationFilter oraz JwtAuthorizationFilter (ten ma się łańcuszku pojawić zaraz za JwtAuthenticationFilter)
                // TODO #2 ustaw politykę zarządzania sesjami na stateless
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

       // auth.inMemoryAuthentication().withUser("user").password("password").roles("ADMIN");

        auth.userDetailsService(/* custom implementation of functional interface UserDetailsService */ username -> {
            final Optional<User> user = userRepository.findByEmail(username);
            return user.isPresent()
                    ? new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), user.get().getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()))
                    : null;
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // just for test purposes
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
