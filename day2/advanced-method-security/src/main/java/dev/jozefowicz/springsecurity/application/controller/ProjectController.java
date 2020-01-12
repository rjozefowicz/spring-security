package dev.jozefowicz.springsecurity.application.controller;

import dev.jozefowicz.springsecurity.application.model.Project;
import dev.jozefowicz.springsecurity.application.repository.OrganizationRepository;
import dev.jozefowicz.springsecurity.application.repository.ProjectRepository;
import dev.jozefowicz.springsecurity.application.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/organizations/{organizationId}/projects")
public class ProjectController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SecurityService securityService;

    @GetMapping
    public List<Project> listAll(@PathVariable("organizationId") long organizationId) {
        final List<Project> projects = organizationRepository.findById(organizationId).orElseThrow(() -> new IllegalArgumentException()).getProjects();
        if (securityService.isRoot()) {
            return projects;
        } else {
            final String authenticatedUserEmail = securityService.authenticatedUserEmail();
            return projects.stream()
                .filter(project -> project.getUsers().stream().anyMatch(u -> u.getEmail().equals(authenticatedUserEmail)))
                .collect(Collectors.toList());
        }
    }

    @GetMapping
    @RequestMapping("/{projectId}")
    public Project findById(@PathVariable long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

}
