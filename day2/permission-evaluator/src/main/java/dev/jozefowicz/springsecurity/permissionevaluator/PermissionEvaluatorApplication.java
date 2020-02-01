package dev.jozefowicz.springsecurity.permissionevaluator;

import dev.jozefowicz.springsecurity.permissionevaluator.model.Project;
import dev.jozefowicz.springsecurity.permissionevaluator.model.ProjectPermission;
import dev.jozefowicz.springsecurity.permissionevaluator.model.ProjectPermissionType;
import dev.jozefowicz.springsecurity.permissionevaluator.model.User;
import dev.jozefowicz.springsecurity.permissionevaluator.repository.ProjectPermissionRepository;
import dev.jozefowicz.springsecurity.permissionevaluator.repository.ProjectRepository;
import dev.jozefowicz.springsecurity.permissionevaluator.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PermissionEvaluatorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(PermissionEvaluatorApplication.class, args);
		ProjectRepository projectRepository = applicationContext.getBean(ProjectRepository.class);
		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		ProjectPermissionRepository projectPermissionRepository = applicationContext.getBean(ProjectPermissionRepository.class);

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

		projectPermissionRepository.persist(new ProjectPermission(user1, project1, ProjectPermissionType.READ));
		projectPermissionRepository.persist(new ProjectPermission(user1, project1, ProjectPermissionType.WRITE));

		projectPermissionRepository.persist(new ProjectPermission(user2, project1, ProjectPermissionType.WRITE));
		projectPermissionRepository.persist(new ProjectPermission(user2, project1, ProjectPermissionType.READ));
		projectPermissionRepository.persist(new ProjectPermission(user2, project3, ProjectPermissionType.READ));

		projectPermissionRepository.persist(new ProjectPermission(user3, project1, ProjectPermissionType.READ));

		projectPermissionRepository.persist(new ProjectPermission(user4, project1, ProjectPermissionType.READ));
		projectPermissionRepository.persist(new ProjectPermission(user4, project2, ProjectPermissionType.READ));
		projectPermissionRepository.persist(new ProjectPermission(user4, project3, ProjectPermissionType.READ));

		projectPermissionRepository.persist(new ProjectPermission(user5, project2, ProjectPermissionType.READ));
		projectPermissionRepository.persist(new ProjectPermission(user5, project2, ProjectPermissionType.WRITE));

		projectPermissionRepository.persist(new ProjectPermission(user6, project3, ProjectPermissionType.READ));
		projectPermissionRepository.persist(new ProjectPermission(user6, project3, ProjectPermissionType.WRITE));

		// TODO przetestuj probujac dostawac sie do roznych zasobow wykorzystujac np POSTMANA. Uzywamy tutaj HTTP Basic
	}



}
