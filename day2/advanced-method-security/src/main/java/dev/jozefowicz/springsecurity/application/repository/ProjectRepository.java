package dev.jozefowicz.springsecurity.application.repository;

import dev.jozefowicz.springsecurity.application.model.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProjectRepository implements Repository<Project> {

    private List<Project> projects = new ArrayList<>();

    @Override
    public void persist(Project entity) {
        projects.add(entity);
    }

    @Override
    public List<Project> findAll() {
        return projects;
    }

    @Override
    public Optional<Project> findById(long id) {
        return projects.stream().filter(p -> p.getId() == id).findAny();
    }
}
