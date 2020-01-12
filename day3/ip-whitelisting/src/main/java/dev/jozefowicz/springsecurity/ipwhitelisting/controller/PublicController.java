package dev.jozefowicz.springsecurity.ipwhitelisting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping
    public String welcome() {
        return "Welcome in the jungle!";
    }

}
