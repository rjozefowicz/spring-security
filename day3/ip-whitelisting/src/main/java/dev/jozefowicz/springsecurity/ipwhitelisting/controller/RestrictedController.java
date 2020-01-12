package dev.jozefowicz.springsecurity.ipwhitelisting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restricted")
public class RestrictedController {

    @GetMapping
    public String restricted() {
        return "Restricted access";
    }

}
