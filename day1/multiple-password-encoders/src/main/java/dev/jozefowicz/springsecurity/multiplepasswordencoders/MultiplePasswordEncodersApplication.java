package dev.jozefowicz.springsecurity.multiplepasswordencoders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO multiple password encoders
 *
 * - Zdefiniuj PasswordEncodery jako beany Springa
 * 		- Pbkdf2PasswordEncoder
 * 		- StandardPasswordEncoder
 * 		- BCryptPasswordEncoder
 * - Zdefiniuj DelegatingPasswordEncodera. Skonfiguruj w nim trzy wcześniej stworzone PasswordEncodery z prefixami kolejno pbkdf2, sha256, bcrypt
 * 	 bcrypt powinien być domyślnym PasswordEncoderem
 * - Stwórz trzech użytkowników (np in memory) i każdemu przypisz hasło zenkodowane jednym z PasswordEncoderów. Hasła poprzedź prefixem w {..}
 *   np {sha256}xdsesffs
 *   Wykorzystaj np UserDetailsService i implementację InMemoryUserDetailsManager do zdefiniowania userów
 * - Spróbuj się zalogować kolejnymi użytkownikami. Podejrzyj na debugu DelegatingPasswordEncoder
 */

@SpringBootApplication
public class MultiplePasswordEncodersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiplePasswordEncodersApplication.class, args);
	}

}
