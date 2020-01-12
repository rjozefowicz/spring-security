package dev.jozefowicz.springsecurity.permissionevaluator.model;

public class ProjectPermission {

    private User user;
    private Project project;
    private ProjectPermissionType permissionType;

    public ProjectPermission() {
    }

    public ProjectPermission(User user, Project project, ProjectPermissionType permissionType) {
        this.user = user;
        this.project = project;
        this.permissionType = permissionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectPermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(ProjectPermissionType permissionType) {
        this.permissionType = permissionType;
    }
}
