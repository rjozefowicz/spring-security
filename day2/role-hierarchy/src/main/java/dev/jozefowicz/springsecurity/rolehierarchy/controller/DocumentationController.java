package dev.jozefowicz.springsecurity.rolehierarchy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/docs")
public class DocumentationController {

    private String documentation = "initial";

    @GetMapping
    @PreAuthorize("hasRole('READER')")
    public String read() {
        return documentation;
    }

    @PostMapping
    @PreAuthorize("hasRole('EDITOR')")
    public void write(@RequestBody @NotEmpty String documentation) {
        this.documentation = documentation;
    }

}
