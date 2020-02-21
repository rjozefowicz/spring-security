package dev.jozefowicz.springsecurity;

import dev.jozefowicz.springsecurity.config.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Przykład pokazujący jak napisać migrację użytkowników na nowe hasła
 * 1. Definiujemy DelegatingPasswordEncodera z dwoma password encoderami. Standard dla legacy haseł, BCrypt dla nowych haseł
 * 2. w application.properties mamy property, którym możemy sterować czy chcemy migrować hasła czy nie
 * 3. definiujemy listenera ApplicationListener<AuthenticationSuccessEvent>, który wywola się po poprawnym zuwieżytelnieniu
 * 	  Będzie wyciągał z obiektu Authentication hasło użytkownika i je encodował nowym encoderem.
 * 	  Dzięki fladze authBuilder.eraseCredentials(!migrateUserPassword) możemy sterować procesem czyszczenia hasła z obiektu
 * 	  Authentication
 * 4. zdefiniowalismy UserPasswordMigration encje, trzymamy w niej informację, że użytkownik już zmigrował na nowe hasło
 * 5. nie ma sensu za każdym razem nadpisywać hasła więc sprawdzamy przed migracją czy użytkownik już się zmigrował
 * 6. endpoint do testów localhost:8080/login
 * 7. wylogowywanie poprzez usunięcie ciastka JSESSIONID
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(new Class[]{Application.class, SecurityConfiguration.class}, args);
	}

}