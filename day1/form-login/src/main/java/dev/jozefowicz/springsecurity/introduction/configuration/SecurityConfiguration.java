package dev.jozefowicz.springsecurity.introduction.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * TODO
 * 1. Własny formularz logowania. Stwórz login.html. Stwórz konfigurację security, mapowania w kontrolerze. Ważne jest wykorzystanie dobrych nazw z formularza
 *    (username, password jako nazwy przesyłanych parametrów) oraz postowanie na endpoint /login. Dodanie permitAll na „/login"
 * 2. Możemy nadpisać domyślne nazwy atrybutów z formularza oraz endpoint na który wykonujemy POST. Możemy to zrobić aby ukryć fakt, że używamy spring security pod spodem
 *      - zmień nazwy atrybutów na user / pass a endpoint na /doLogin
 * 3. Dodamy własną stronę ze złą próbą logowania. Domyślne zachowanie to redirect na /login?error. Stwórz stronę invalidLogin.html, odpowiednie mapowanie w kontrolerze oraz konfigurację formLogin()
 * 4. Ustawimy domyślną stronę po zalogowaniu na /portal (możesz dodać dodatkowe mapowanie w kontrolerze, które zwróci cokolwiek). Możemy skonfigurować to aby zawsze tam trafiać przekazując jako drugi
 *    parametr true. Zazwyczaj tego nie chcemy. Dostając się poprzez adres xyz.com/abc chcemy po zalogowaniu wrócić na podstronę /abc
 * 5. Dodamy funkcję logouta. Jest tak samo konfigurowalna jak formularz logowania. Ważne jest to, że jak mamy włączoną obronę przed CSRF to działa wyłącznie /logout POST.
 *    Możemy dla próby wyłączyć aby przetestować. Dodaj do index.html buttona, który będzie POST-ował na endpoint /logout. Zmień endpoint /logout na /doLogout
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin();
    }
}
