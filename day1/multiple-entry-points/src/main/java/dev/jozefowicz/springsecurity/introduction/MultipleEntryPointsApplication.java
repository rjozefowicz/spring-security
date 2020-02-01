package dev.jozefowicz.springsecurity.introduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 TODO
 1. Stwórz nową klasę konfiguracyjną (rozszerzającą WebSecurityConfigurerAdapter)
 2. Skonfiguruj nowe UserDetailsService dla jednego użytkownika (np admin-jan/admin00)
 3. Skonfiguruj Security dla ścieżek /admin/** jako http basic
 4. Ustaw odpowiednią kolejność klas WebSecurityConfigurerAdapter (adnotacja @Order)
 5. W rezultacie powinniśmy dostać /admin/** zabezpieczone jako HTTP BASIC oraz /portal/** zabezpieczone klasycznym formularzem logowania
 */
@SpringBootApplication
public class MultipleEntryPointsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleEntryPointsApplication.class, args);
	}

}
