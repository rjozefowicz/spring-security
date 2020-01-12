package dev.jozefowicz.springsecurity.junit5demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

    @GetMapping("/lebron")
    @PreAuthorize("hasRole('LEBRON')")
    public void lebronOnly() {
        System.out.println("lebron only invocation");
    }

    @GetMapping("/kobe")
    @PreAuthorize("hasRole('KOBE')")
    public void kobeOnly() {
        System.out.println("kobe only invocation");
    }

}
