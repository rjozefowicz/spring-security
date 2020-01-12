package dev.jozefowicz.springsecurity.acldemo;

import dev.jozefowicz.springsecurity.acldemo.model.Project;
import dev.jozefowicz.springsecurity.acldemo.model.User;
import dev.jozefowicz.springsecurity.acldemo.repository.ProjectRepository;
import dev.jozefowicz.springsecurity.acldemo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AclDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AclDemoApplication.class, args);

		ProjectRepository projectRepository = applicationContext.getBean(ProjectRepository.class);
		UserRepository userRepository = applicationContext.getBean(UserRepository.class);

		Project project1 = new Project(1, "project1");
		Project project2 = new Project(2, "project2");
		Project project3 = new Project(3, "project3");
		projectRepository.persist(project1);
		projectRepository.persist(project2);
		projectRepository.persist(project3);

		User user1 = new User(1, "jan@example.com", "password");
		User user2 = new User(2, "stefan@example.com", "password");
		User user3 = new User(3, "kalina@example.com", "password");
		User user4 = new User(4, "joanna@example.com", "password");
		User user5 = new User(5, "marcelina@example.com", "password");
		User user6 = new User(6, "katarzyna@example.com", "password");

		userRepository.persist(user1);
		userRepository.persist(user2);
		userRepository.persist(user3);
		userRepository.persist(user4);
		userRepository.persist(user5);
		userRepository.persist(user6);
	}

}
