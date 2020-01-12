package dev.jozefowicz.springsecurity.jwt;

import dev.jozefowicz.springsecurity.jwt.domain.Book;
import dev.jozefowicz.springsecurity.jwt.domain.User;
import dev.jozefowicz.springsecurity.jwt.repository.BookRepository;
import dev.jozefowicz.springsecurity.jwt.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class IntroductionApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(IntroductionApplication.class, args);

		final BookRepository bookRepository = context.getBean(BookRepository.class);
		bookRepository.persist(Book.of("Dwunasta Planeta", "Zecharia Sitchin", "9788386096664"));
		bookRepository.persist(Book.of("Schody do nieba", "Zecharia Sitchin", "9788364193996"));
		bookRepository.persist(Book.of("House of Cards", "Michael Dobbs", "9788324047390"));

		final UserRepository userRepository = context.getBean(UserRepository.class);
		userRepository.persist(User.of("jan@example.com", "Jan Kowalski", "MyPassword", Arrays.asList("ADMIN", "USER")));
		userRepository.persist(User.of("stefan@example.com", "Stafan Kisielewski", "MyPassword1", Arrays.asList("USER")));
	}

}
