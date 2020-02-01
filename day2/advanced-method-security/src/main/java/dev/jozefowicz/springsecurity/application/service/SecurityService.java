package dev.jozefowicz.springsecurity.application.service;

import dev.jozefowicz.springsecurity.application.repository.OrganizationRepository;
import dev.jozefowicz.springsecurity.application.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ProjectRepository projectRepository;

// TODO #1 zaimplementuj metodę isRoot()
    // TODO #2 zaimplementuj metodę String authenticatedUserEmail()
    // TODO #3 zaimplementuj metodę canAccessOrganization(long organizationId)
    // TODO #4 zaimplementuj metodę boolean canAccessProject(long projectId)


}
