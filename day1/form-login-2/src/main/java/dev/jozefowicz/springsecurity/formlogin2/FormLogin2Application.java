package dev.jozefowicz.springsecurity.formlogin2;

import dev.jozefowicz.springsecurity.formlogin2.domain.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class FormLogin2Application {

	public static void main(String[] args) {
		final ConfigurableApplicationContext applicationContext = SpringApplication.run(FormLogin2Application.class, args);
		final UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		userRepository.persist(new UserRepository.User("jan", "kowalski", List.of("ROLE_ADMIN")));
		userRepository.persist(new UserRepository.User("stefan", "kisielewski", List.of("ROLE_USER")));
	}

}
