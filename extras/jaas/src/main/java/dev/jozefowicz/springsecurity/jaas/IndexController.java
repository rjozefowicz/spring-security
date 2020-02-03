package dev.jozefowicz.springsecurity.jaas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/all")
    public String user() {
        return "admin and user";
    }

}
