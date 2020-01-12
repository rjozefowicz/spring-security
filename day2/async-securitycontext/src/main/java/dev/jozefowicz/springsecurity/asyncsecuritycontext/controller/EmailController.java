package dev.jozefowicz.springsecurity.asyncsecuritycontext.controller;

import dev.jozefowicz.springsecurity.asyncsecuritycontext.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/messages")
    public void sendMessage(@NotEmpty @RequestBody String message) {
        emailService.sendEmail(message);
    }

}
