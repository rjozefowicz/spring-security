package dev.jozefowicz.springsecurity.application.service;

import dev.jozefowicz.springsecurity.application.model.Organization;
import dev.jozefowicz.springsecurity.application.model.Project;
import dev.jozefowicz.springsecurity.application.repository.OrganizationRepository;
import dev.jozefowicz.springsecurity.application.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public boolean isRoot() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();














        User user = (User) authentication.getPrincipal();
        return user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ROOT"));
    }















    public String authenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }












    public boolean canAccessOrganization(long organizationId) {
        Organization organization =
                organizationRepository.findById(organizationId).orElseThrow(() -> new IllegalArgumentException(""));
        return organization
                .getUsers()
                .stream()
                .anyMatch(u -> u.getEmail().equals(authenticatedUserEmail()));
    }














    public boolean canAccessProject(long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException(""));
        return project.getUsers().stream().anyMatch(u -> u.getEmail().equals(authenticatedUserEmail()));
    }

}
