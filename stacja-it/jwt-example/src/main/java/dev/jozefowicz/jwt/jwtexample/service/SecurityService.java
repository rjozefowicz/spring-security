package dev.jozefowicz.jwt.jwtexample.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static dev.jozefowicz.jwt.jwtexample.model.Repository.organizationUsers;

@Service
public class SecurityService {

    /**
     * TODO sprawdź czy user ma dostęp do organizacji, wykorzystaj listę organizarionUsers
     * @param id
     * @return
     */
    public boolean canAccessOrganization(long id) {
        return true;
    }

}
