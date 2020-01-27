package dev.jozefowicz.springsecurity.introduction.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * TODO
 * Zadanie #1 - zdefiniuj użytkownika in memory nadpisując metodę configure(AuthenticationManagerBuilder auth). Usuń propertiesy z application.properties
 * Zadanie #2 - Zakomentuj konfigurację in memory i zdefiniuj własną implementację UserDetailsService.
 *              - jeżeli nazwa użytkownika to jan, zwróć nowe UserDetails (możesz wykorzystać klasę PersistedUserDetails albo klasę User z Spring Security)
 *              - W przeciwnym wypadku rzuć wyjątkiem. Pamiętaj, że UserDetailsService nie może zwracać null
 * Zadanie #3 - Jest już skonfigurowana baza danych in memory H2. Możesz się zalogować do konsoli poprzez endpoint /h2-console. Jest też zależność do Spring Data na classpathie
 *              - Stwórz encję JPA User z id, username, password
 *              - Stwórz repozytorium UserRepository zwracające użytkowników po nazwie
 *              - Zakomentuj (usuń) aktualną implementację UserDetailsService i wykorzystaj tym razem UserRepository
 *              - zapoznaj się z plikiem data.sql
 * Zadanie #4 - Stworz własny Serwis / Komponent Springowy implementujący UserDetailsManager
 *              - przenieś swoją implementację UserDetailsService do nowej klasy
 *              - zaimplementuj metodę createUser
 *              - Stwórz metodą np setup() z adnotacją @PostConstruct w SecurityConfiguration i dodaj nowego użytkownika
 *              - Podmień w SecurityConfiguration referencję do userDetailsService na swoją nową implementację UserDetailsManagera
 */


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

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
                // TODO ustawienia potrzebne wyłącznie dla bazy H2. Wyłączamy CSRF na endpoint /h2-console/**
                .csrf(csrf -> {
                    csrf
                            .ignoringAntMatchers("/h2-console/**");
                })
                // TODO konsola H2 używa mechanizmu iframe więc pozwalamy na używanie ramek w tym samym originie. Domyślne ustawienia Frame Options mają na celu ochronę przed atakami clickjacking
                .headers(headers -> {
                    headers.frameOptions().sameOrigin();
                });
    }

}
