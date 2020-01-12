package dev.jozefowicz.springsecurity.application.repository;

import dev.jozefowicz.springsecurity.application.model.Organization;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrganizationRepository implements Repository<Organization> {

    private List<Organization> organizations = new ArrayList<>();

    @Override
    public void persist(Organization entity) {
        organizations.add(entity);
    }

    @Override
    public List<Organization> findAll() {
        return organizations;
    }

    @Override
    public Optional<Organization> findById(long id) {
        return organizations.stream().filter(o -> o.getId() == id).findAny();
    }
}
