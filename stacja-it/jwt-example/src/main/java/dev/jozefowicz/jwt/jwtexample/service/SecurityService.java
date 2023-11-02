package dev.jozefowicz.jwt.jwtexample.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static dev.jozefowicz.jwt.jwtexample.model.Repository.organizationUsers;

@Service
public class SecurityService {

    public boolean canAccessOrganization(long id) {
        return organizationUsers.stream().anyMatch(ou -> ou.organizationId() == id && ou.userEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

}
