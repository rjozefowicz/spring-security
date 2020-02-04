package dev.jozefowicz.springsecurity.jwt.domain;

import java.util.List;

public class User {
    private String email;
    private String name;
    private String password;
    private List<String> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static User of(String email, String name, String password, List<String> roles) {
        User user = new User();
        user.email = email;
        user.name = name;
        user.password = password;
        user.roles = roles;
        return user;
    }
}