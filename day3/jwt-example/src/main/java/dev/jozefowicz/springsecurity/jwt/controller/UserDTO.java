package dev.jozefowicz.springsecurity.jwt.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class UserDTO {
    String email;
    String name;
}
