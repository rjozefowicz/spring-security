package dev.jozefowicz.springsecurity.introduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO:
 * * Publicznie dostępna strona z formularzem logowania /singin
 * * Publicznie dostępna strona z formularzem rejestracji /signup
 * * Publicznie dostępny endpoint z /verify?token= do potwierdzenia konta
 * * Użytkownik musi potwierdzić konto klikając na jednorazowy link. Link albo sam token wypisz do logów
 * * Wykorzystaj albo implementację in memory albo bazę H2 (zależność do H2 i starter do Spring Data JPA)
 * * Spring Security
 *     * Własna konfiguracja
 *     * Własny implementacja UserDetailsService
 *     * Dowolny PasswordEncoder
 *     * Opcjonalnie opcja remember me
 * * Wiele możliwych implementacji
 */
@SpringBootApplication
public class RegistrationFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationFlowApplication.class, args);
	}

}
