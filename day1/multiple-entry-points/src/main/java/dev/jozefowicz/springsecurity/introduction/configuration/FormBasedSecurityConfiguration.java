package dev.jozefowicz.springsecurity.introduction.configuration;

import dev.jozefowicz.springsecurity.introduction.domain.User;
import dev.jozefowicz.springsecurity.introduction.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.Optional;

@EnableWebSecurity
@Order(2)
public class FormBasedSecurityConfiguration extends WebSecurityConfigurerAdapter {

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
                            .antMatchers("/portal/login", "/portal/invalidLogin", "/h2-console/**")
                            .permitAll();
                })
                .antMatcher("/portal/**")
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(formLogin -> {
                    formLogin
                            .loginPage("/portal/login")
                            .usernameParameter("user")
                            .passwordParameter("pass")
                            .loginProcessingUrl("/portal/doLogin")
                            .failureUrl("/portal/invalidLogin")
                            .defaultSuccessUrl("/portal");
                })
                .logout(logout -> {
                    logout
                            .logoutUrl("/portal/doLogout")
                            .logoutSuccessUrl("/portal/login?loggedOut");
                })
                .csrf(csrf -> {
                    csrf
                            .ignoringAntMatchers("/h2-console/**");
                })
                .headers(headers -> {
                    headers.frameOptions().disable();
                })
                .rememberMe(rememberMe -> {
                    rememberMe
                            .key("some-key")
                            .rememberMeCookieName("rememberMe")
                            .rememberMeParameter("rememberMe")
                            .tokenRepository(tokenRepository());
                });
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        return jdbcTokenRepositoryImpl;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
