package dev.jozefowicz.springsecurity.introduction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public Map<String, Object> index() {
        return Map.of("Welcome", "in the jungle");
    }
}
