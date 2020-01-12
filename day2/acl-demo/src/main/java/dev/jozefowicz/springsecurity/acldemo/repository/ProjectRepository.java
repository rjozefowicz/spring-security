package dev.jozefowicz.springsecurity.acldemo.repository;

import dev.jozefowicz.springsecurity.acldemo.model.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
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
        return new ArrayList<>(projects); // copy of project list as ACL implementation modifies collections. It is not an issue while doing real JDBC interactions
    }

    @Override
    public Optional<Project> findById(long id) {
        return projects.stream().filter(p -> p.getId() == id).findAny();
    }
}
