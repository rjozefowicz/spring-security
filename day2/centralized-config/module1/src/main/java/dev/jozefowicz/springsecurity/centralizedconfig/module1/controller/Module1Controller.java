package dev.jozefowicz.springsecurity.centralizedconfig.module1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module1")
public class Module1Controller {

    @GetMapping
    public String module1() {
        return "module1";
    }

}
