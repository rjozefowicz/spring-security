package dev.jozefowicz.springsecurity.permissionevaluator.configuration;

import dev.jozefowicz.springsecurity.permissionevaluator.model.Project;
import dev.jozefowicz.springsecurity.permissionevaluator.model.ProjectPermissionType;
import dev.jozefowicz.springsecurity.permissionevaluator.repository.ProjectPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DomainPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private ProjectPermissionRepository projectPermissionRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object domainObject, Object permission) {
        User principal = (User) authentication.getPrincipal();

        if (domainObject instanceof Project) {
            return hasPermissionToProject(principal.getUsername(), ((Project) domainObject).getId(), ProjectPermissionType.valueOf(permission.toString()));
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable domainObjectId, String domainObjectType, Object permission) {
        User principal = (User) authentication.getPrincipal();

        if (domainObjectType.equals(Project.class.getName())) {
            return hasPermissionToProject(principal.getUsername(), (Long) domainObjectId, ProjectPermissionType.valueOf(permission.toString()));
        }

        return false;
    }

    private boolean hasPermissionToProject(String email, long projectId, ProjectPermissionType type) {
        return projectPermissionRepository
            .findAll()
            .stream()
            .anyMatch(projectPermission -> projectPermission.getProject().getId() == projectId && projectPermission.getUser().getEmail().equals(email) && type == projectPermission.getPermissionType());
    }
}
