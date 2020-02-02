package dev.jozefowicz.springsecurity.sessionmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * TODO
 * 1. Ustaw maksymalny czas trwania sesji na 1m i przetestuj - property aplikacyjny
 * 2. Ustaw maksymalnie jedną aktywną sesję dla użytkownika i przetestuj logując się w oknie incognito
 * 3. Zdefiniuj session expiration link w ustawieniach sesji na /session/expired. Dodaj odpowiednie mapowanie w kontrolerze SessionController oraz w ustawieniach security
 * 4. Zdefiniuj invalid session link na /session/invalid i przetestuj - np modyfikując JSESSIONID. Dodaj mapowania w kontrolerze i ustawieniach security
 * 5. Poexperymentuj z ustawieniami sessionFixation(). Zmień na none(). Co się zmieniło z JSESSIONID?
 * 6. Zdefiniuj beana SessionRegistry i dodaj do konfiguracji sessionManagement()
 * 7. Zaimplementuj w IndexControllerze dwie metody invalidate oraz zwróć w index() użytkowników z ich sesjami. Wykorzystaj zdefiniowane DTO i wydmuszki metod.
 * 	  Wstrzyknij SessionRegistry do pobierania informacji o sesjach
 * 8. Dodaj propertiesy:
 * 		server.servlet.session.cookie.http-only=true
 * 		server.servlet.session.cookie.secure=true
 */
@SpringBootApplication
public class SessionManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionManagementApplication.class, args);
	}

}

