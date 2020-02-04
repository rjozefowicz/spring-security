package dev.jozefowicz.springsecurity.jwt.controller;

public class UserDTO {
    private String email;
    private String name;

    public UserDTO() {
    }

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

    public static UserDTO of(String email, String name) {
        UserDTO dto = new UserDTO();
        dto.email = email;
        dto.name = name;
        return dto;
    }
}
