package dev.jozefowicz.springsecurity.permissionevaluator.repository;

import dev.jozefowicz.springsecurity.permissionevaluator.model.ProjectPermission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectPermissionRepository {

    private List<ProjectPermission> organizations = new ArrayList<>();

    public void persist(ProjectPermission entity) {
        organizations.add(entity);
    }

    public List<ProjectPermission> findAll() {
        return organizations;
    }

}
