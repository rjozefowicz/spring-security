package dev.jozefowicz.springsecurity.application.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    private long id;
    private List<User> users = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    public Organization() {
    }

    public Organization(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
