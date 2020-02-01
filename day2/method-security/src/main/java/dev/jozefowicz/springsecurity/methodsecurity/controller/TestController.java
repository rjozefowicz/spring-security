package dev.jozefowicz.springsecurity.methodsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @PostMapping
    @Secured("TODO")
    public void admin() {
        System.out.println("admin endpoint");
    }

    @GetMapping
    @Secured("TODO")
    public String user() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
