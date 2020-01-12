package dev.jozefowicz.springsecurity.jwt.config;

import lombok.Data;

@Data
public class CredentialsDTO {
    private String email;
    private String password;
}
