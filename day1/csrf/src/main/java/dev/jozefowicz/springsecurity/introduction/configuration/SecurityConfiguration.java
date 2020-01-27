package dev.jozefowicz.springsecurity.introduction.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * TODO
 *
 * - W pliku index.html pojawił się nowy formularz do dodawania nowych książek. Spróbuj dodać nową książkę
 * - Podejrzeć w konsoli developerskiej chrome co jest dokładnie w formularzu wysyłane. Domyślnie Spring Security ma włączone zabezpieczenie przed atakami CSRF. Na debugu można podejrzeć
 *   filtr CsrfFilter
 *
 * Zadanie #1 - Wyłącz ochronę przed atakami CSRF i ponownie podejrzyj co jest wysyłane w requeście do serwera
 * Zadanie #2 - Włącz ponownie ochronę przed atakami CSRF i wypisz na stronie token CSRF
 * Zadanie #3 - Nie zawsze możemy korzystać z gotowych integracji tak jak ma Thymeleaf (do podejrzenia klasa CsrfRequestDataValueProcessor). Napisz formularz bez wykorzystania przestrzeni
 *              nazw Thymeleaf (atrybuty z th:xyz) i własnoręcznie dołącz token CSRF do requesta
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .antMatchers("/login", "/invalidLogin")
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
                });
    }
}
