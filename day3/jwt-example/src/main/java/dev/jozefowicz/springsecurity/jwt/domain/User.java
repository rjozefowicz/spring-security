package dev.jozefowicz.springsecurity.jwt.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor(staticName = "of")
public class User {
    String email;
    String name;
    String password;
    List<String> roles;
}
