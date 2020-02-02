package dev.jozefowicz.springsecurity.jwtadvanced.controller;

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
    // TODO użytkownik z rolą ADMIN albo USER
    public List<String> books() {
        return Arrays.asList("book1", "book2");
    }

    @PostMapping
    // TODO tylko ADMIN
    public void newBook() {
        System.out.println("adding new book");
    }


}
