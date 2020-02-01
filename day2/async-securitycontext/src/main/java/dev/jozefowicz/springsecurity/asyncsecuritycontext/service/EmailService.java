package dev.jozefowicz.springsecurity.asyncsecuritycontext.service;

import dev.jozefowicz.springsecurity.asyncsecuritycontext.controller.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final SecurityService securityService;

    public EmailService(SecurityService securityService) {
        this.securityService = securityService;
    }

    // TODO wykonaj metodę asynchronicznie
    public void sendEmail(String message) {
        System.out.println(String.format("Sending email to %s with message: %s", message, securityService.getUsername()));
    }

}
