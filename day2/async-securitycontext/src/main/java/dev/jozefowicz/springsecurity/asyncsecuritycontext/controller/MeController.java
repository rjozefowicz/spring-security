package dev.jozefowicz.springsecurity.asyncsecuritycontext.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

    private final SecurityService securityService;

    public MeController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/me")
    public String me() {
        return securityService.getUsername();
    }

}
