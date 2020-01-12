package dev.jozefowicz.springsecurity.application;

import dev.jozefowicz.springsecurity.application.model.Organization;
import dev.jozefowicz.springsecurity.application.model.Project;
import dev.jozefowicz.springsecurity.application.model.User;
import dev.jozefowicz.springsecurity.application.repository.OrganizationRepository;
import dev.jozefowicz.springsecurity.application.repository.ProjectRepository;
import dev.jozefowicz.springsecurity.application.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class ResourceSecurityApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ResourceSecurityApplication.class, args);

		OrganizationRepository organizationRepository = applicationContext.getBean(OrganizationRepository.class);
		ProjectRepository projectRepository = applicationContext.getBean(ProjectRepository.class);
		UserRepository userRepository = applicationContext.getBean(UserRepository.class);

		Organization organization1 = new Organization(1);
		Organization organization2 = new Organization(2);
		organizationRepository.persist(organization1);
		organizationRepository.persist(organization2);

		Project project1 = new Project(1, "project1");
		Project project2 = new Project(2, "project2");
		Project project3 = new Project(3, "project3");
		projectRepository.persist(project1);
		projectRepository.persist(project2);
		projectRepository.persist(project3);

		organization1.getProjects().add(project1);
		organization1.getProjects().add(project2);
		organization2.getProjects().add(project3);

		User user1 = new User(1, "jan@example.com", "password", Arrays.asList("ROLE_ROOT")); // ROOT user bez organizacji. Nie robić produkcyjnie :)
		User user2 = new User(2, "stefan@example.com", "password", Arrays.asList("ROLE_ADMIN"));
		project1.getUsers().add(user2);
		organization1.getUsers().add(user2);
		User user3 = new User(3, "kalina@example.com", "password", Arrays.asList("ROLE_USER"));
		project1.getUsers().add(user3);
		organization1.getUsers().add(user3);
		User user4 = new User(4, "joanna@example.com", "password", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
		project2.getUsers().add(user4);
		organization1.getUsers().add(user4);
		User user5 = new User(5, "marcelina@example.com", "password", Arrays.asList("ROLE_ADMIN"));
		project3.getUsers().add(user5);
		organization2.getUsers().add(user5);
		User user6 = new User(6, "katarzyna@example.com", "password", Arrays.asList("ROLE_USER"));
		project3.getUsers().add(user6);
		organization2.getUsers().add(user6);

		userRepository.persist(user1);
		userRepository.persist(user2);
		userRepository.persist(user3);
		userRepository.persist(user4);
		userRepository.persist(user5);
		userRepository.persist(user6);
	}

}
