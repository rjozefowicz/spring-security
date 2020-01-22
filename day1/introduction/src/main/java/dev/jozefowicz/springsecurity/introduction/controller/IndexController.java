package dev.jozefowicz.springsecurity.introduction.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public Map<String, Object> index() {
        return Map.of("Welcome", SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @GetMapping("/contact")
    public Map<String, Object> contact() {
        return Map.of("email", "contact@example.com", "phone", "+48 999 888 777");
    }

}
