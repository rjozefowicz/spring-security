package dev.jozefowicz.jwt.jwtexample.controller;

import dev.jozefowicz.jwt.jwtexample.model.Organization;
import dev.jozefowicz.jwt.jwtexample.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public List<Organization> getAll() {
        return organizationService.getOrganizations(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public void create(@RequestBody NewOrganizationDto dto) {
        this.organizationService.addOrganization(dto.name());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getById(@PathVariable("id") long id) {
        var organization = organizationService.getById(id);
        if (organization.isPresent()) {
            return ResponseEntity.ok(organization.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
