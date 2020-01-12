package dev.jozefowicz.springsecurity.application.controller;

import dev.jozefowicz.springsecurity.application.model.Organization;
import dev.jozefowicz.springsecurity.application.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping
    public List<Organization> listAll() {
        return organizationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Organization findById(@PathVariable Long id) {
        return organizationRepository.findById(id).orElse(null);
    }

    @PostMapping
    public void add(@RequestBody Organization organization) {
        organizationRepository.persist(organization);
    }

}
