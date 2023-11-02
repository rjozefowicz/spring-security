package dev.jozefowicz.jwt.jwtexample.service;

import dev.jozefowicz.jwt.jwtexample.model.Organization;
import dev.jozefowicz.jwt.jwtexample.model.OrganizationUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dev.jozefowicz.jwt.jwtexample.model.Repository.organizationUsers;
import static dev.jozefowicz.jwt.jwtexample.model.Repository.organizations;

@Service
public class OrganizationService {

    public List<Organization> getOrganizations(String userEmail) {
        var organizationIds = organizationUsers.stream().filter(ou -> ou.userEmail().equals(userEmail)).map(ou -> ou.organizationId()).collect(Collectors.toList());
        return organizations.stream().filter(o -> organizationIds.contains(o.id())).toList();
    }

    @PreAuthorize("@securityService.canAccessOrganization(#id)")
    public Optional<Organization> getById(long id) {
        return organizations.stream().filter(organization -> organization.id() == id).findFirst();
    }

    public void addOrganization(String name) {
        var currentMaxId = organizations.stream().map(organization -> organization.id()).mapToLong(id -> id).max().getAsLong();
        organizations.add(new Organization(currentMaxId + 1l, name));
        organizationUsers.add(new OrganizationUser(currentMaxId + 1l, SecurityContextHolder.getContext().getAuthentication().getName()));
    }

}
