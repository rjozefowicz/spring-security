package dev.jozefowicz.springsecurity.multiplepasswordencoders.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public ResponseEntity index() {
        return ResponseEntity.ok(Map.of("user", SecurityContextHolder.getContext().getAuthentication().getName()));
    }

}
