package dev.jozefowicz.springsecurity.asyncsecuritycontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Przykład używa HTTP Basic
// TODO #1 - Włącz asynchroniczne wykonywanie metod
// TODO #3:
//		- Włącz propagowanie SecurityContextHoldera	między wątkami. Są co najmniej trzy opcje ;-)
@SpringBootApplication
public class AsyncSecurityContextApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncSecurityContextApplication.class, args);
	}

}
