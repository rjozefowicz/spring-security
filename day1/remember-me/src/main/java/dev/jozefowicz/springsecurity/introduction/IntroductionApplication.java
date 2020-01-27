package dev.jozefowicz.springsecurity.introduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO
 * Zadanie #1
 * 		- Włącz w SecurityConfig opcję rememberMe
 * 		- Dodaj na formularzu logowania (login.html) checkboxa z atrybutem name="remember-me" - jest to domyślna wartość oczekiwana przez Spring Security
 * 		- Sprawdź w opcjach konsoli developerskiej chrome ciasteczka. Czy pojawiło się nowe? Spróbuj usunąć JSESSIONID i odśwież stronę. Zdekoduj w base64 dekoderze ciasteczko remember-me
 *
 * 	Zadanie #2
 * 		- Customizacja ustawień remember me. Zmień klucz na "some-key", nazwę ciasteczka na "rememberMe" i nazwę parametru z formularza logowania na "rememberMe". Nanieś odpowiednie zmiany w
 * 		  login.html
 *
 * 	Zadanie #3
 * 		- Implementacje bezpieczniejszej wersji z trzymaniem tokena w bazie danych
 * 		- Przeanalizuj plik data.sql
 * 		- Zdefiniuj TokenRepository - implementacja JdbcTokenRepositoryImpl i wstrzykinj DataSource (jest już dostępny w kontekście Springa). Referencję do repozytorium ustaw ustawieniach
 * 		  remember me
 * 		- Przeanalizuj ponownie token zapisywany w przeglądarce. Co się zmieniło? Przeanalizuj tabelę persistent_logins
 */

@SpringBootApplication
public class IntroductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroductionApplication.class, args);
	}

}
