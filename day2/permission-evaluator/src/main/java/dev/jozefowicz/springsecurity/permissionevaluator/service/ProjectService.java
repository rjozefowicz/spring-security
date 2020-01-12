package dev.jozefowicz.springsecurity.permissionevaluator.service;

import dev.jozefowicz.springsecurity.permissionevaluator.model.Project;
import dev.jozefowicz.springsecurity.permissionevaluator.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<Project> listAll() {
        return projectRepository.findAll();
    }

    @PreAuthorize("hasPermission(#project, 'READ')")
    public void read(Project project) {
        System.out.println("Could read project");
    }

    @PreAuthorize("hasPermission(#id, 'dev.jozefowicz.springsecurity.permissionevaluator.model.Project', 'READ')")
    public Project readById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new IllegalStateException());
    }

    @PreAuthorize("hasPermission(#project, 'WRITE')")
    public void update(Project project) {
        projectRepository.findById(project.getId()).ifPresent(p -> {
            p.setName(project.getName());
        });
    }

    @PreAuthorize("hasPermission(#id, 'dev.jozefowicz.springsecurity.permissionevaluator.model.Project', 'WRITE')")
    public void updateNameById(Long id, String name) {
        projectRepository.findById(id).ifPresent(p -> {
            p.setName(name);
        });
    }

}
