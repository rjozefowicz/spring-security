package dev.jozefowicz.springsecurity.acldemo.service;

import dev.jozefowicz.springsecurity.acldemo.model.Project;
import dev.jozefowicz.springsecurity.acldemo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public void read(@Param("project") Project project) {
        System.out.println("Could read project");
    }

    @PreAuthorize("hasPermission(#id, 'dev.jozefowicz.springsecurity.acldemo.model.Project', 'READ')")
    public Project readById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new IllegalStateException());
    }

    @PreAuthorize("hasPermission(#project, 'WRITE')")
    public void update(@Param("project") Project project) {
        projectRepository.findById(project.getId()).ifPresent(p -> {
            p.setName(project.getName());
        });
    }

}
