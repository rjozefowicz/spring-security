package dev.jozefowicz.springsecurity.application.controller;

import dev.jozefowicz.springsecurity.application.model.Project;
import dev.jozefowicz.springsecurity.application.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/organizations/{organizationId}/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Project> listAll(@PathVariable("organizationId") long organizationId) {

        // TODO pobierz wszystkie projekty z organizacji o organizationId.
        // Jeżeli użytkownik jest rootem zwróć wszystkie, jeżeli nie to odfiltruj te projekty, do których użytkownik nie należy
        // wykorzystaj securityService

        return Collections.emptyList();
    }

    @GetMapping
    @RequestMapping("/{projectId}")
    public Project findById(@PathVariable long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

}
