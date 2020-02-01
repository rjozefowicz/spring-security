package dev.jozefowicz.springsecurity.centralizedconfig.application.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * TODO #2:
 * 1. Tworzymy klasę rozszerzającą GlobalMethodSecurityConfiguration
 * 2. Dodajemy adnotację @EnableGlobalMethodSecurity
 * 3. Nadpisujemy metodę GlobalMethodSecurityConfiguration.customMethodSecurityMetadataSource()
 * 4. Skonfiguruj
 *      - MeController.me() dla ról USER i ADMIN
 *      - Module1Controller.module1() dla roli ADMIN
 * 5. Wykorzystujemy HTTP BASIC
 * 6. Przydatny link https://docs.spring.io/spring-security/site/docs/5.1.2.RELEASE/reference/htmlsingle/#explicit-methodsecurityinterceptor-configuration
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("user1")
                .authorities("ROLE_USER", "ROLE_ADMIN")
                .and()
                .withUser("user2")
                .password("user2")
                .authorities("ROLE_USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests.anyRequest().authenticated();
                })
                .httpBasic();
    }

}
