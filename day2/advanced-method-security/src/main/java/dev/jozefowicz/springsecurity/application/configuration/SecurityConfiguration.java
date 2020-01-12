package dev.jozefowicz.springsecurity.application.configuration;

import dev.jozefowicz.springsecurity.application.model.User;
import dev.jozefowicz.springsecurity.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/organizations/{organizationId}/projects/{projectId}/**")
                .access("hasRole('ROOT') or (@securityService.canAccessOrganization(#organizationId) and @securityService.canAccessProject(#projectId))")
            .antMatchers("/organizations/{organizationId}/**")
                .access("hasRole('ROOT') or @securityService.canAccessOrganization(#organizationId)")
            .antMatchers("/organizations/**")
                .hasRole("ROOT")
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(""));
            return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
