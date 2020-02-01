package dev.jozefowicz.springsecurity.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public List<String> books(Principal principal) {
        System.out.println("Books requested by: " + principal.getName());
        return List.of("book1", "book2");
    }

}
