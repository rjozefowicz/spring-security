package dev.jozefowicz.springsecurity.application.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    // TODO metoda dostępna dla użytkowników z rolami ADMIN i USER
    public List<String> books() {
        return Arrays.asList("book1", "book2");
    }

    @PostMapping
    // TODO metoda dostępna tylko dla użytkowników z rolą ADMIN
    public void newBook() {
        System.out.println("adding new book");
    }


}

