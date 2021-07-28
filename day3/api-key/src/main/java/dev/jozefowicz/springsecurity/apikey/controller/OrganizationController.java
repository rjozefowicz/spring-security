package dev.jozefowicz.springsecurity.apikey.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private List<Organization> organizations = new CopyOnWriteArrayList<>();

    @GetMapping
    public ResponseEntity<Map<String, List<Organization>>> listAll() {
        return ResponseEntity.ok(Collections.singletonMap("organizations", organizations));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateOrganizationRequest request){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        organizations.add(new Organization(UUID.randomUUID().toString(), request.getName(), nonNull(authentication) ? authentication.getName() : null));
        return ResponseEntity.ok().build();
    }

    public static final class CreateOrganizationRequest {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static final class Organization {
        private final String id;
        private final String name;
        private final String createdBy;

        public Organization(String id, String name, String createdBy) {
            this.id = id;
            this.name = name;
            this.createdBy = createdBy;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCreatedBy() {
            return createdBy;
        }
    }
}
