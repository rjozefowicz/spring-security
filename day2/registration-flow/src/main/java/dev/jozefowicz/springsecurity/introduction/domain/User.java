package dev.jozefowicz.springsecurity.introduction.domain;

public class User {

    private String name;
    private String password;
    private boolean enabled;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.enabled = false;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
