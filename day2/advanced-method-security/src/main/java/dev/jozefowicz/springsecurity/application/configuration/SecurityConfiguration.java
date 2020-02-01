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
                // TODO #1 zdefiniuj następujące uprawnienia:
                /*
                    1. /organizations/{organizationId}/projects/{projectId}/** -> dostęp ma ROOT + wywołaj securityService.canAccessOrganization() + securityService.canAccessProject
                    2. /organizations/{organizationId}/** -> dostęp ma ROOT + securityService.canAccessOrganization
                    3. /organizations/** -> dostęp ma tylko ROOT
                    pamiętaj, że kolejność ma znaczenie
                 */
            .anyRequest()
            .authenticated();
            // TODO #2 włącz http basic
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
