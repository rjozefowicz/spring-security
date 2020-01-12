package dev.jozefowicz.springsecurity.x509.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/books")
public class UserController {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<String> books() {
        return Arrays.asList("Dwunasta Planeta", "Schody do nieba", "Zaginione krolestwa");
    }

}
